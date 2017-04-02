package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.list.ListUnit;
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
public class UnitGetway {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    SQL sql = new SQL();
    public void save(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("insert into " + this.db + ".Unit values(?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, unit.unitName);
            this.pst.setString(3, unit.unitDescription);
            this.pst.setString(4, unit.creatorId);
            this.pst.setString(5, LocalDate.now().toString());
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Unit  '" + unit.unitName + "' Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void view(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Unit");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                unit.id = this.rs.getString(1);
                unit.unitName = this.rs.getString(2);
                unit.unitDescription = this.rs.getString(3);
                unit.creatorId = this.rs.getString(4);
                unit.date = this.rs.getString(5);
                unit.creatorName = this.sql.getName(unit.creatorId, unit.creatorName, "User");
                unit.unitDetails.addAll(new ListUnit[]{new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void selectedView(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Unit where id=?");
            this.pst.setString(1, unit.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                unit.id = this.rs.getString(1);
                unit.unitName = this.rs.getString(2);
                unit.unitDescription = this.rs.getString(3);
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchView(Unit unit) {
        this.con = this.dbCon.getConnection();
        unit.unitDetails.clear();
        try {
            this.con = this.dbCon.getConnection();
            this.pst = this.con.prepareCall("select * from " + this.db + ".Unit where UnitName like ? ORDER BY UnitName");
            this.pst.setString(1, "%" + unit.unitName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                unit.id = this.rs.getString(1);
                unit.unitName = this.rs.getString(2);
                unit.unitDescription = this.rs.getString(3);
                unit.creatorId = this.rs.getString(4);
                unit.date = this.rs.getString(5);
                unit.creatorName = this.sql.getName(unit.creatorId, unit.creatorName, "User");
                unit.unitDetails.addAll(new ListUnit[]{new ListUnit(unit.id, unit.unitName, unit.unitDescription, unit.creatorName, unit.date)});
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Unit where Id=? and UnitName=?");
            this.pst.setString(1, unit.id);
            this.pst.setString(2, unit.unitName);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("Into the loop");
                updateNow(unit);
                return;
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
            if (isUniqName(unit)) {
                System.out.println("Out of the loop");
                updateNow(unit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateNow(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("update " + this.db + ".Unit set UnitName=? , UnitDescription=? where Id=?");
            this.pst.setString(1, unit.unitName);
            this.pst.setString(2, unit.unitDescription);
            this.pst.setString(3, unit.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Unit  '" + unit.unitName + "' Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Unit unit) {
        this.con = this.dbCon.getConnection();
        deleteParmanently(unit);
    }
    public boolean isUniqName(Unit unit) {
        this.con = this.dbCon.getConnection();
        boolean uniqBrand = false;
        try {
            this.pst = this.con.prepareCall("select * from " + this.db + ".Unit where UnitName=?");
            this.pst.setString(1, unit.unitName);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("in not uniq");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : Already exist");
                alert.setContentText("Unit  '" + unit.unitName + "' Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return uniqBrand;
            }
            this.pst.close();
            this.con.close();
            this.rs.close();
            uniqBrand = true;
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uniqBrand;
    }
    public void deleteParmanently(Unit unit) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareCall("delete from " + this.db + ".Unit where Id=?");
            this.pst.setString(1, unit.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Supplyer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean isNotUse(Unit unit) {
        this.con = this.dbCon.getConnection();
        boolean isNotUse = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Products where UnitId=?");
            this.pst.setString(1, unit.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("This Unit use in '" + this.rs.getString(2) + "' product \n delete product first");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUse;
            }
            this.pst.close();
            this.rs.close();
            this.con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            Logger.getLogger(UnitGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isNotUse;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.getway\UnitGetway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */