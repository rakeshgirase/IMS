package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.list.ListCatagory;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.database.SQL;
import com.exuberant.ims.util.PropertyService;
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
public class CatagoryGetway {
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        catagory.brandName = this.sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.brandId = this.sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = this.sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Catagory values(?,?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, catagory.catagoryName);
            this.pst.setString(3, catagory.catagoryDescription);
            this.pst.setString(4, catagory.brandId);
            this.pst.setString(5, catagory.supplyerId);
            this.pst.setString(6, catagory.creatorId);
            this.pst.setString(7, LocalDate.now().toString());
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Category  '" + catagory.catagoryName + "' Added Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void view(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Catagory");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                catagory.id = this.rs.getString(1);
                catagory.catagoryName = this.rs.getString(2);
                catagory.catagoryDescription = this.rs.getString(3);
                catagory.brandId = this.rs.getString(4);
                catagory.supplyerId = this.rs.getString(5);
                catagory.creatorId = this.rs.getString(6);
                catagory.date = this.rs.getString(7);
                catagory.brandName = this.sql.getName(catagory.brandId, catagory.brandName, "Brands");
                catagory.supplyerName = this.sql.getName(catagory.supplyerId, catagory.supplyerName, "Supplyer");
                catagory.creatorName = this.sql.getName(catagory.creatorId, catagory.catagoryName, "User");
                catagory.catagoryDetails.addAll(new ListCatagory[]{new ListCatagory(catagory.id, catagory.catagoryName, catagory.catagoryDescription, catagory.brandName, catagory.supplyerName, catagory.creatorName, catagory.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectedView(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where Id=?");
            this.pst.setString(1, catagory.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                catagory.id = this.rs.getString(1);
                catagory.catagoryName = this.rs.getString(2);
                catagory.catagoryDescription = this.rs.getString(3);
                catagory.brandId = this.rs.getString(4);
                catagory.supplyerId = this.rs.getString(5);
                catagory.brandName = this.sql.getName(catagory.brandId, catagory.brandName, "Brands");
                catagory.supplyerName = this.sql.getName(catagory.supplyerId, catagory.supplyerName, "Supplyer");
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void brandView(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where SupplyerId=?");
            this.pst.setString(1, catagory.supplyerId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                catagory.brandName = this.rs.getString(2);
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchView(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        catagory.catagoryDetails.clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where CatagoryName like ? ORDER BY CatagoryName");
            this.pst.setString(1, "%" + catagory.catagoryName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                catagory.id = this.rs.getString(1);
                catagory.catagoryName = this.rs.getString(2);
                catagory.catagoryDescription = this.rs.getString(3);
                catagory.brandId = this.rs.getString(4);
                catagory.supplyerId = this.rs.getString(5);
                catagory.creatorId = this.rs.getString(6);
                catagory.date = this.rs.getString(7);
                catagory.brandName = this.sql.getName(catagory.brandId, catagory.brandName, "Brands");
                catagory.supplyerName = this.sql.getName(catagory.supplyerId, catagory.supplyerName, "Supplyer");
                catagory.creatorName = this.sql.getName(catagory.creatorId, catagory.catagoryName, "User");
                catagory.catagoryDetails.addAll(new ListCatagory[]{new ListCatagory(catagory.id, catagory.catagoryName, catagory.catagoryDescription, catagory.brandName, catagory.supplyerName, catagory.creatorName, catagory.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        catagory.brandId = this.sql.getIdNo(catagory.brandName, catagory.brandId, "Brands", "BrandName");
        catagory.supplyerId = this.sql.getIdNo(catagory.supplyerName, catagory.supplyerId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".Catagory set CatagoryName=? , CatagoryDescription=?,BrandId=?,SupplyerId=? where Id=?");
            this.pst.setString(1, catagory.catagoryName);
            this.pst.setString(2, catagory.catagoryDescription);
            this.pst.setString(3, catagory.brandId);
            this.pst.setString(4, catagory.supplyerId);
            this.pst.setString(5, catagory.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Update : update sucess");
            alert.setContentText("Category  '" + catagory.catagoryName + "' Update Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("delete from " + this.db + ".Catagory where Id=?");
            this.pst.setString(1, catagory.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isNotUse(Catagory catagory) {
        this.con = this.dbCon.getConnection();
        boolean isNotUse = false;
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Products where CatagoryId=?");
            this.pst.setString(1, catagory.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROE : Already exist ");
                alert.setContentText("Category  '" + this.rs.getString(2) + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            this.pst.close();
            this.rs.close();
            this.con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(CatagoryGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
