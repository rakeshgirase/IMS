package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.list.ListSupplyer;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
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
public class SupplyerGetway {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        if (isUniqSupplyerName(supplyer)) {
            try {
                this.con = this.dbCon.getConnection();
                this.pst = this.con.prepareCall("insert into " + this.db + ".Supplyer values(?,?,?,?,?,?,?)");
                this.pst.setString(1, null);
                this.pst.setString(2, supplyer.supplyerName);
                this.pst.setString(3, supplyer.supplyerContactNumber);
                this.pst.setString(4, supplyer.supplyerAddress);
                this.pst.setString(5, supplyer.supplyerDescription);
                this.pst.setString(6, supplyer.creatorId);
                this.pst.setString(7, LocalDate.now().toString());
                this.pst.executeUpdate();
                this.con.close();
                this.pst.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Supplier  '" + supplyer.supplyerName + "' Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } catch (SQLException ex) {
                Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void view(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                supplyer.id = this.rs.getString(1);
                supplyer.supplyerName = this.rs.getString(2);
                supplyer.supplyerContactNumber = this.rs.getString(3);
                supplyer.supplyerAddress = this.rs.getString(4);
                supplyer.supplyerDescription = this.rs.getString(5);
                supplyer.creatorId = this.rs.getString(6);
                supplyer.date = this.rs.getString(7);
                supplyer.supplyerDetails.addAll(new ListSupplyer[]{new ListSupplyer(supplyer.id, supplyer.supplyerName, supplyer.supplyerContactNumber, supplyer.supplyerAddress, supplyer.supplyerDescription, supplyer.date)});
            }
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            System.out.println("Exception");
        }
    }
    public void searchView(Supplyer supplyer) {
        supplyer.supplyerDetails.clear();
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Supplyer where SupplyerName like ? or SupplyerPhoneNumber like ? ORDER BY SupplyerName");
            this.pst.setString(1, "%" + supplyer.supplyerName + "%");
            this.pst.setString(2, "%" + supplyer.supplyerName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                supplyer.id = this.rs.getString(1);
                supplyer.supplyerName = this.rs.getString(2);
                supplyer.supplyerContactNumber = this.rs.getString(3);
                supplyer.supplyerAddress = this.rs.getString(4);
                supplyer.supplyerDescription = this.rs.getString(5);
                supplyer.creatorId = this.rs.getString(6);
                supplyer.date = this.rs.getString(7);
                supplyer.supplyerDetails.addAll(new ListSupplyer[]{new ListSupplyer(this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), this.rs.getString(4), this.rs.getString(5), this.rs.getString(7))});
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectedView(Supplyer supplyer) {
        System.out.println("name :" + supplyer.supplyerName);
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Supplyer where id=?");
            this.pst.setString(1, supplyer.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                supplyer.id = this.rs.getString(1);
                supplyer.supplyerName = this.rs.getString(2);
                supplyer.supplyerContactNumber = this.rs.getString(3);
                supplyer.supplyerAddress = this.rs.getString(4);
                supplyer.supplyerDescription = this.rs.getString(5);
                supplyer.creatorId = this.rs.getString(6);
                supplyer.date = this.rs.getString(7);
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Supplyer supplyer) {
        System.out.println("we are in update");
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer where Id=? and SupplyerName=?");
            this.pst.setString(1, supplyer.id);
            this.pst.setString(2, supplyer.supplyerName);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("Into the loop");
                updateNow(supplyer);
                this.rs.close();
                this.pst.close();
                this.con.close();
                return;
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
            if (isUniqSupplyerName(supplyer)) {
                System.out.println("Out of the loop");
                updateNow(supplyer);
                this.rs.close();
                this.con.close();
                this.pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("SELECT * FROM " + this.db + ".Brands WHERE SupplyerId=?");
            this.pst.setString(1, supplyer.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("This supplier provide some brands, please delete these brand first! Is that nessary to delete this supplyer ? \nif not you can update supplyer as much you can");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return;
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
            deleteParmanently(supplyer);
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isUniqSupplyerName(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        boolean uniqSupplyer = false;
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareCall("select SupplyerName from " + this.db + ".Supplyer where SupplyerName=?");
            this.pst.setString(1, supplyer.supplyerName);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Supplier  '" + supplyer.supplyerName + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return uniqSupplyer;
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
            uniqSupplyer = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqSupplyer;
    }
    public void updateNow(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".Supplyer set SupplyerName=? , SupplyerPhoneNumber=?,SupplyerAddress=? ,SuplyerDescription=? where Id=?");
            this.pst.setString(1, supplyer.supplyerName);
            this.pst.setString(2, supplyer.supplyerContactNumber);
            this.pst.setString(3, supplyer.supplyerAddress);
            this.pst.setString(4, supplyer.supplyerDescription);
            this.pst.setString(5, supplyer.id);
            this.pst.executeUpdate();
            this.con.close();
            this.pst.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Supplier  '" + supplyer.supplyerName + "' Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteParmanently(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        try {
            System.out.println("and i am hear");
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("delete from " + this.db + ".Supplyer where Id=?");
            this.pst.setString(1, supplyer.id);
            this.pst.executeUpdate();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean isUpdate(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        boolean isUpdate = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer where Id=?");
            this.pst.setString(1, supplyer.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    public boolean isNotUse(Supplyer supplyer) {
        this.con = this.dbCon.getConnection();
        boolean isNotUse = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where SupplyerId=?");
            this.pst.setString(1, supplyer.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("WARNING");
                alert.setHeaderText("WARNING : ");
                alert.setContentText("This Supplier supplied  '" + this.rs.getString(2) + "' brand \n delete brand first");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplyerGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.getway\SupplyerGetway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */