package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Brands;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.list.ListBrands;
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
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
public class BrandsGetway {
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(Brands brands) {
        this.con = this.dbCon.getConnection();
        brands.supplyrId = this.sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Brands values(?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, brands.brandName);
            this.pst.setString(3, brands.brandComment);
            this.pst.setString(4, brands.supplyrId);
            this.pst.setString(5, brands.creatorId);
            this.pst.setString(6, LocalDate.now().toString());
            this.pst.executeUpdate();
            this.con.close();
            this.pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Brand  '" + brands.brandName + "' Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void view(Brands brands) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Brands");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                brands.id = this.rs.getString(1);
                brands.brandName = this.rs.getString(2);
                brands.brandComment = this.rs.getString(3);
                brands.supplyrId = this.rs.getString(4);
                brands.creatorId = this.rs.getString(5);
                brands.date = this.rs.getString(6);
                brands.supplyerName = this.sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
                brands.creatorName = this.sql.getName(brands.creatorId, brands.creatorName, "User");
                brands.brandDitails.addAll(new ListBrands[]{new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date)});
            }
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectedView(Brands brands) {
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Brands where id=?");
            this.pst.setString(1, brands.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                brands.id = this.rs.getString(1);
                brands.brandName = this.rs.getString(2);
                brands.brandComment = this.rs.getString(3);
                brands.supplyrId = this.rs.getString(4);
                brands.supplyerName = this.sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
            }
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchView(Brands brands) {
        this.con = this.dbCon.getConnection();
        brands.brandDitails.clear();
        System.out.println("name :" + brands.brandName);
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Brands where BrandName like ? ORDER BY BrandName");
            System.out.println("Brand name in Brand Object");
            this.pst.setString(1, "%" + brands.brandName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                brands.id = this.rs.getString(1);
                brands.brandName = this.rs.getString(2);
                brands.brandComment = this.rs.getString(3);
                brands.supplyrId = this.rs.getString(4);
                brands.creatorId = this.rs.getString(5);
                brands.date = this.rs.getString(6);
                brands.supplyerName = this.sql.getName(brands.supplyrId, brands.supplyerName, "Supplyer");
                brands.creatorName = this.sql.getName(brands.creatorId, brands.creatorName, "User");
                brands.brandDitails.addAll(new ListBrands[]{new ListBrands(brands.id, brands.brandName, brands.brandComment, brands.supplyerName, brands.creatorName, brands.date)});
            }
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(Brands brands) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("delete from " + this.db + ".Brands where Id=?");
            this.pst.setString(1, brands.id);
            this.pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Brands brands) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".Brands set BrandName=? , Description=?,SupplyerId=? where Id=?");
            this.pst.setString(1, brands.brandName);
            this.pst.setString(2, brands.brandComment);
            this.pst.setString(3, brands.supplyrId);
            this.pst.setString(4, brands.id);
            this.pst.executeUpdate();
            this.con.close();
            this.pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Update : update sucess ");
            alert.setContentText("Update  '" + brands.brandName + "' Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isNotUsed(Brands brands) {
        this.con = this.dbCon.getConnection();
        boolean inNotUse = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where BrandId=?");
            this.pst.setString(1, brands.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROE : Already exist ");
                alert.setContentText("Brand  '" + brands.brandName + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return inNotUse;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            inNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(BrandsGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inNotUse;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.getway\BrandsGetway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */