package com.exuberant.ims.bll;

import com.exuberant.ims.dal.Brands;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.database.SQL;
import com.exuberant.ims.getway.BrandsGetway;
import com.exuberant.ims.util.PropertyService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BrandBLL {
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");
    BrandsGetway brandsGetway = new BrandsGetway();

    public void save(Brands brands) {
        if (isUniqName(brands)) {
            this.brandsGetway.save(brands);
        }
    }

    public void update(Brands brands) {
        if (isTrueUpdate(brands)) {
            this.brandsGetway.update(brands);
        } else if (isUniqName(brands)) {
            this.brandsGetway.update(brands);
        }
    }

    public void delete(Brands brands) {
        if (this.brandsGetway.isNotUsed(brands)) {
            this.brandsGetway.delete(brands);
        }
    }

    public boolean isTrueUpdate(Brands brands) {
        boolean isTreu = false;
        brands.supplyrId = this.sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");
        System.out.println("we are in update");
        try {
            this.pst = this.con.prepareStatement("SELECT * FROM " + this.db + ".Brands WHERE BrandName =? AND SupplyerId =? AND Id =?");
            this.pst.setString(1, brands.brandName);
            this.pst.setString(2, brands.supplyrId);
            this.pst.setString(3, brands.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return isTreu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTreu;
    }

    public boolean isUniqName(Brands brands) {
        boolean uniqSupplyer = false;
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Brands where BrandName=? and SupplyerId=?");
            brands.supplyrId = this.sql.getIdNo(brands.supplyerName, brands.supplyrId, "Supplyer", "SupplyerName");
            this.pst.setString(1, brands.brandName);
            this.pst.setString(2, brands.supplyrId);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("Brand  '" + brands.brandName + "' Already exist");
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