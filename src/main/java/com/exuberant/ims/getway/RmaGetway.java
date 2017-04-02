package com.exuberant.ims.getway;
import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.list.ListRma;
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
public class RmaGetway {
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");

    public void save(RMA rma) {
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("insert into " + this.db + ".RMA values(?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, rma.rmaName);
            this.pst.setString(3, rma.rmaDays);
            this.pst.setString(4, rma.rmaComment);
            this.pst.setString(5, rma.creatorId);
            this.pst.setString(6, LocalDate.now().toString());
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Unit  '" + rma.rmaName + "' Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void view(RMA rma) {
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".RMA");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                rma.id = this.rs.getString(1);
                rma.rmaName = this.rs.getString(2);
                rma.rmaDays = this.rs.getString(3);
                rma.rmaComment = this.rs.getString(4);
                rma.creatorId = this.rs.getString(5);
                rma.date = this.rs.getString(6);
                rma.creatorName = this.sql.getName(rma.creatorId, rma.creatorName, "User");
                rma.rmaDetails.addAll(new ListRma[]{new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectedView(RMA rma) {
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".RMA where id=?");
            this.pst.setString(1, rma.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                rma.id = this.rs.getString(1);
                rma.rmaName = this.rs.getString(2);
                rma.rmaDays = this.rs.getString(3);
                rma.rmaComment = this.rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchView(RMA rma) {
        rma.rmaDetails.clear();
        System.out.println("name :" + rma.rmaName);
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".RMA where RMAName like ? or RMADays like ? ORDER BY RMAName");
            this.pst.setString(1, "%" + rma.rmaName + "%");
            this.pst.setString(2, "%" + rma.rmaName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                rma.id = this.rs.getString(1);
                rma.rmaName = this.rs.getString(2);
                rma.rmaDays = this.rs.getString(3);
                rma.rmaComment = this.rs.getString(4);
                rma.creatorId = this.rs.getString(5);
                rma.date = this.rs.getString(6);
                rma.creatorName = this.sql.getName(rma.creatorId, rma.creatorName, "User");
                rma.rmaDetails.addAll(new ListRma[]{new ListRma(rma.id, rma.rmaName, rma.rmaDays, rma.rmaComment, rma.creatorName, rma.date)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(RMA rma) {
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".RMA set RMAName=? , RMADays=?, Comment=? where Id=?");
            this.pst.setString(1, rma.rmaName);
            this.pst.setString(2, rma.rmaDays);
            this.pst.setString(3, rma.rmaComment);
            this.pst.setString(4, rma.id);
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Unit  '" + rma.rmaName + "' Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(RMA rma) {
        this.con = this.dbCon.getConnection();
        try {
            System.out.println("and i am hear");
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("delete from " + this.db + ".RMA where Id=?");
            this.pst.setString(1, rma.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isUniqName(RMA rma) {
        this.con = this.dbCon.getConnection();
        boolean uniqRMA = false;
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".RMA where RMAName=? or RMADays=?");
            this.pst.setString(1, rma.rmaName);
            this.pst.setString(2, rma.rmaDays);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : save sucess");
                alert.setContentText("RMA  '" + rma.rmaName + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return uniqRMA;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            uniqRMA = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqRMA;
    }
    public boolean isNotUse(RMA rma) {
        this.con = this.dbCon.getConnection();
        boolean isNotUse = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where RMAId=?");
            this.pst.setString(1, rma.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : save sucess");
                alert.setContentText("This RMA use in product'" + this.rs.getString(2) + "'  \n delete product first");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(RmaGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
