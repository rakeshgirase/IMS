package com.exuberant.ims.database;
import com.exuberant.ims.controller.application.employe.AddEmployeController;
import com.exuberant.ims.controller.application.stock.ViewSupplyerController;
import com.exuberant.ims.util.PropertyService;
import javafx.scene.control.Label;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SQL {
    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    String db = PropertyService.getInstance().getProperty("db");

    public void registration(String id, String userName, String fullName, String emailAddress, String contactNumber, String salary, String address, String password, int status, String date, String crratorId, String imagePath) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".User values(?,?,?,?,?,?,?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, userName);
            this.pst.setString(3, fullName);
            this.pst.setString(4, emailAddress);
            this.pst.setString(5, contactNumber);
            this.pst.setString(6, salary);
            this.pst.setString(7, address);
            this.pst.setString(8, password);
            this.pst.setString(9, "1");
            if (imagePath != null) {
                System.out.println("I am hear");
                try {
                    InputStream is = new FileInputStream(new File(imagePath));
                    this.pst.setBlob(10, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AddEmployeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.pst.setBlob(10, (Blob) null);
            }
            this.pst.setString(11, LocalDate.now().toString());
            this.pst.setString(12, id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void userPermissionUpdate(int id) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement(null);
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void basicPermission(String usrName) {
        DBConnection dbc = new DBConnection();
        this.con = dbc.getConnection();
        try {
            this.pst = this.con.prepareStatement("Select Id FROM " + this.db + ".User where UsrName=?");
            this.pst.setString(1, usrName);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.pst = this.con.prepareStatement("insert into " + this.db + ".UserPermission values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                this.pst.setString(1, null);
                this.pst.setInt(2, 1);
                this.pst.setInt(3, 1);
                this.pst.setInt(4, 1);
                this.pst.setInt(5, 1);
                this.pst.setInt(6, 1);
                this.pst.setInt(7, 1);
                this.pst.setInt(8, 1);
                this.pst.setInt(9, 1);
                this.pst.setInt(10, 1);
                this.pst.setInt(11, 1);
                this.pst.setInt(12, 1);
                this.pst.setInt(13, 1);
                this.pst.setInt(14, 1);
                this.pst.setInt(15, 1);
                this.pst.setInt(16, 1);
                this.pst.setInt(17, 1);
                this.pst.setInt(18, 1);
                this.pst.setInt(19, 1);
                this.pst.setInt(20, this.rs.getInt("Id"));
                this.pst.executeUpdate();
            }
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void creatorNameFindar(String creatorId, Label creatorName) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".User where Id=?");
            this.pst.setString(1, creatorId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                creatorName.setText(this.rs.getString(2));
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ViewSupplyerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getName(String id, String name, String tableName) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + "." + tableName + " where Id=?");
            this.pst.setString(1, id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                name = this.rs.getString(2);
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public String getIdNo(String name, String id, String tableName, String fieldName) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + "." + tableName + " where " + fieldName + " =?");
            this.pst.setString(1, name);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                id = this.rs.getString(1);
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public String getBrandID(String supplyerId, String brandId, String brandName) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where SupplyerId=? and BrandName=?");
            this.pst.setString(1, supplyerId);
            this.pst.setString(2, brandName);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                brandId = this.rs.getString(1);
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brandId;
    }
    public String getCatagoryId(String supplyerId, String brandId, String catagoryId, String catagoryName) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where SupplyerId=? and BrandId=? and CatagoryName=?");
            this.pst.setString(1, supplyerId);
            this.pst.setString(2, brandId);
            this.pst.setString(3, catagoryName);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                catagoryId = this.rs.getString(1);
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catagoryId;
    }
    public String getDayes(String rmaDayes, String id) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".RMA where id=?");
            this.pst.setString(1, id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                rmaDayes = this.rs.getString(3);
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rmaDayes;
    }
}
