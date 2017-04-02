package com.exuberant.ims.bll;
import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.getway.CatagoryGetway;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
import com.exuberant.ims.database.SQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CatagoryBLL {
    SQL sql = new SQL();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(Catagory catagory) {
        if (isUniqName(catagory)) {
            this.catagoryGetway.save(catagory);
        }
    }
    public void update(Catagory catagory) {
        if (checkUpdate(catagory)) {
            this.catagoryGetway.update(catagory);
        } else if (isUniqName(catagory)) {
            this.catagoryGetway.update(catagory);
        }
    }
    public void delete(Catagory catagory) {
        if (this.catagoryGetway.isNotUse(catagory)) {
            this.catagoryGetway.delete(catagory);
        }
    }
    public boolean checkUpdate(Catagory catagory) {
        boolean isTrueUpdate = false;
        catagory.brandId = this.sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = this.sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where CatagoryName=? and BrandId=? and SupplyerId=? and Id=?");
            this.pst.setString(1, catagory.catagoryName);
            this.pst.setString(2, catagory.brandId);
            this.pst.setString(3, catagory.supplyerId);
            this.pst.setString(4, catagory.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return isTrueUpdate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrueUpdate;
    }
    public boolean isUniqName(Catagory catagory) {
        boolean uniqSupplyer = false;
        catagory.brandId = this.sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = this.sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Catagory where CatagoryName=? and BrandId=? and SupplyerId=?");
            this.pst.setString(1, catagory.catagoryName);
            this.pst.setString(2, catagory.brandId);
            this.pst.setString(3, catagory.supplyerId);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("Catagory  '" + catagory.catagoryName + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return uniqSupplyer;
            }
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\CatagoryBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */