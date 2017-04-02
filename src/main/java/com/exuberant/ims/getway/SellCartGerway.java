package com.exuberant.ims.getway;
import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.list.ListSold;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.database.SQL;
import com.exuberant.ims.util.PropertyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SellCartGerway {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(SellCart sellCart) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Sell values(?,?,?,?,?,?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, sellCart.sellID);
            this.pst.setString(3, sellCart.customerID);
            this.pst.setString(4, sellCart.productID);
            this.pst.setString(5, sellCart.pursesPrice);
            this.pst.setString(6, sellCart.sellPrice);
            this.pst.setString(7, sellCart.quantity);
            this.pst.setString(8, sellCart.totalPrice);
            this.pst.setString(9, sellCart.warrentyVoidDate);
            this.pst.setString(10, sellCart.sellerID);
            this.pst.setString(11, LocalDateTime.now().toString());
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void view(SellCart sellCart) {
        this.con = this.dbCon.getConnection();
        SQL sql = new SQL();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Sell");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                sellCart.Id = this.rs.getString(1);
                sellCart.sellID = this.rs.getString(2);
                sellCart.customerID = this.rs.getString(3);
                sellCart.productID = this.rs.getString(4);
                sellCart.pursesPrice = this.rs.getString(5);
                sellCart.sellPrice = this.rs.getString(6);
                sellCart.quantity = this.rs.getString(7);
                sellCart.totalPrice = this.rs.getString(8);
                sellCart.warrentyVoidDate = this.rs.getString(9);
                sellCart.sellerID = this.rs.getString(10);
                sellCart.sellDate = this.rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                sellCart.soldList.addAll(new ListSold[]{new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void firstTenView(SellCart sellCart) {
        this.con = this.dbCon.getConnection();
        SQL sql = new SQL();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Sell limit 0,15");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                sellCart.Id = this.rs.getString(1);
                sellCart.sellID = this.rs.getString(2);
                sellCart.customerID = this.rs.getString(3);
                sellCart.productID = this.rs.getString(4);
                sellCart.pursesPrice = this.rs.getString(5);
                sellCart.sellPrice = this.rs.getString(6);
                sellCart.quantity = this.rs.getString(7);
                sellCart.totalPrice = this.rs.getString(8);
                sellCart.warrentyVoidDate = this.rs.getString(9);
                sellCart.sellerID = this.rs.getString(10);
                sellCart.sellDate = this.rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                sellCart.soldList.addAll(new ListSold[]{new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchView(SellCart sellCart) {
        this.con = this.dbCon.getConnection();
        sellCart.carts.clear();
        SQL sql = new SQL();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Sell where SellId like ?");
            this.pst.setString(1, "%" + sellCart.sellID + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                sellCart.Id = this.rs.getString(1);
                sellCart.sellID = this.rs.getString(2);
                sellCart.customerID = this.rs.getString(3);
                sellCart.productID = this.rs.getString(4);
                sellCart.pursesPrice = this.rs.getString(5);
                sellCart.sellPrice = this.rs.getString(6);
                sellCart.quantity = this.rs.getString(7);
                sellCart.totalPrice = this.rs.getString(8);
                sellCart.warrentyVoidDate = this.rs.getString(9);
                sellCart.sellerID = this.rs.getString(10);
                sellCart.sellDate = this.rs.getString(11);
                sellCart.givenProductID = sql.getName(sellCart.productID, sellCart.givenProductID, "Products");
                sellCart.sellerName = sql.getName(sellCart.sellerID, sellCart.sellerName, "User");
                sellCart.customerName = sql.getName(sellCart.customerID, sellCart.customerName, "Customer");
                sellCart.soldList.addAll(new ListSold[]{new ListSold(sellCart.Id, sellCart.sellID, sellCart.productID, sellCart.givenProductID, sellCart.customerID, sellCart.customerName, sellCart.pursesPrice, sellCart.sellPrice, null, sellCart.quantity, sellCart.totalPrice, sellCart.pursrsDate, sellCart.warrentyVoidDate, sellCart.sellerID, sellCart.sellerName, sellCart.sellDate)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
