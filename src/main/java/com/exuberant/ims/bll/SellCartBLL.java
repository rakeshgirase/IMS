package com.exuberant.ims.bll;
import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.getway.SellCartGerway;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.util.PropertyService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SellCartBLL {
    SellCartGerway sellCartGerway = new SellCartGerway();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void sell(SellCart sellCart) {
        updateCurrentQuentity(sellCart);
        this.sellCartGerway.save(sellCart);
    }
    public void updateCurrentQuentity(SellCart sellCart) {
        System.out.println("In Update");
        int oQ = Integer.parseInt(sellCart.oldQuentity);
        int nQ = Integer.parseInt(sellCart.quantity);
        int uQ = oQ - nQ;
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        try {
            System.out.println("In Processing Update");
            this.pst = this.con.prepareStatement("update " + this.db + ".Products set Quantity=? where Id=?");
            this.pst.setString(1, updatedQuentity);
            this.pst.setString(2, sellCart.Id);
            this.pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update Complate");
    }
}