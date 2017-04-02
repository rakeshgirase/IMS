package com.exuberant.ims.bll;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.getway.CurrentProductGetway;
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
public class CurrentProductBLL {
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    SQL sql = new SQL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    public void save(CurrentProduct currentProduct) {
        if (isUniqName(currentProduct)) {
            this.currentProductGetway.save(currentProduct);
        }
    }
    public void update(CurrentProduct currentProduct) {
        if ((isNotNull(currentProduct)) &&
                (isUpdate(currentProduct))) {
            if (checkUpdateCondition(currentProduct)) {
                this.currentProductGetway.update(currentProduct);
            } else if (isUniqName(currentProduct)) {
                this.currentProductGetway.update(currentProduct);
            }
        }
    }
    public boolean isUniqName(CurrentProduct currentProduct) {
        System.out.println("WE ARE IS IS UNIT NAME");
        boolean isUniqname = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where ProductId=?");
            this.pst.setString(1, currentProduct.productId);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Product id  '" + currentProduct.productId + "' id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException localSQLException) {
        }
        return isUniqname;
    }
    public boolean isUpdate(CurrentProduct currentProduct) {
        System.out.println("WE ARE IS IS UPDTE");
        boolean isUpdate = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where Id=? and ProductId=? and ProductName=? and Quantity=? and Description=? and SupplyerId=? and BrandId=? and CatagoryId=? and UnitId=? and PursesPrice=? and SellPrice=? and RMAId=? and Date=?");
            this.pst.setString(1, currentProduct.id);
            this.pst.setString(2, currentProduct.productId);
            this.pst.setString(3, currentProduct.productName);
            this.pst.setString(4, currentProduct.quantity);
            this.pst.setString(5, currentProduct.description);
            this.pst.setString(6, currentProduct.supplierId);
            this.pst.setString(7, currentProduct.brandId);
            this.pst.setString(8, currentProduct.catagoryId);
            this.pst.setString(9, currentProduct.unitId);
            this.pst.setString(10, currentProduct.pursesPrice);
            this.pst.setString(11, currentProduct.sellPrice);
            this.pst.setString(12, currentProduct.rmaId);
            this.pst.setString(13, currentProduct.date);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isUpdate;
    }
    public boolean checkUpdateCondition(CurrentProduct currentProduct) {
        boolean isTrueUpdate = false;
        if (isUpdate(currentProduct)) {
            try {
                this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where id=? and ProductId=?");
                this.pst.setString(1, currentProduct.id);
                this.pst.setString(2, currentProduct.productId);
                this.rs = this.pst.executeQuery();
                if (this.rs.next()) {
                    return isTrueUpdate;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CurrentProduct.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isTrueUpdate;
    }
    public boolean isNotNull(CurrentProduct currentProduct) {
        boolean isNotNull = false;
        if ((currentProduct.productId.isEmpty()) || (currentProduct.sellPrice.isEmpty()) || (currentProduct.quantity.isEmpty())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("ERROR : Null Found");
            alert.setContentText("Please fill requrer field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        }
        return isNotNull;
    }
    public Object delete(CurrentProduct currentProduct) {
        if (this.currentProductGetway.isNotSoled(currentProduct)) {
            this.currentProductGetway.delete(currentProduct);
        }
        return currentProduct;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\CurrentProductBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */