package com.exuberant.ims.getway;
import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.list.ListCustomer;
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
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CustomerGetway {
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(Customer customer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Customer values(?,?,?,?,?,?,?)");
            this.pst.setString(1, null);
            this.pst.setString(2, customer.customerName);
            this.pst.setString(3, customer.customerConNo);
            this.pst.setString(4, customer.customerAddress);
            this.pst.setString(5, customer.totalBuy);
            this.pst.setString(6, customer.userId);
            this.pst.setString(7, LocalDateTime.now().toString());
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess : save sucess");
            alert.setContentText("Customer  '" + customer.customerName + "' Added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void view(Customer customer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                customer.id = this.rs.getString(1);
                customer.customerName = this.rs.getString(2);
                customer.customerConNo = this.rs.getString(3);
                customer.customerAddress = this.rs.getString(4);
                customer.totalBuy = this.rs.getString(5);
                customer.userId = this.rs.getString(6);
                customer.date = this.rs.getString(7);
                customer.userName = this.sql.getName(customer.userId, customer.userName, "User");
                customer.customerList.addAll(new ListCustomer[]{new ListCustomer(customer.id, customer.customerName, customer.customerConNo, customer.customerAddress, customer.totalBuy, customer.userName, customer.date)});
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void selectedView(Customer customer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer where Id=?");
            this.pst.setString(1, customer.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                customer.customerName = this.rs.getString(2);
                customer.customerConNo = this.rs.getString(3);
                customer.customerAddress = this.rs.getString(4);
                customer.totalBuy = this.rs.getString(5);
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void searchView(Customer customer) {
        this.con = this.dbCon.getConnection();
        customer.customerList.clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer where CustomerName like ? or CustomerContNo like ?");
            this.pst.setString(1, "%" + customer.customerName + "%");
            this.pst.setString(2, "%" + customer.customerName + "%");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                customer.id = this.rs.getString(1);
                customer.customerName = this.rs.getString(2);
                customer.customerConNo = this.rs.getString(3);
                customer.customerAddress = this.rs.getString(4);
                customer.totalBuy = this.rs.getString(5);
                customer.userId = this.rs.getString(6);
                customer.date = this.rs.getString(7);
                customer.userName = this.sql.getName(customer.userId, customer.userName, "User");
                customer.customerList.addAll(new ListCustomer[]{new ListCustomer(customer.id, customer.customerName, customer.customerConNo, customer.customerAddress, customer.totalBuy, customer.userName, customer.date)});
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Customer customer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("UPDATE " + this.db + ".Customer set CustomerName=?,CustomerContNo=?,CustomerAddress=? where Id=?");
            this.pst.setString(1, customer.customerName);
            this.pst.setString(2, customer.customerConNo);
            this.pst.setString(3, customer.customerAddress);
            this.pst.setString(4, customer.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Update : Update sucess");
            alert.setContentText("Customer  '" + customer.customerName + "' update successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(Customer customer) {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("delete from " + this.db + ".Customer where id=?");
            this.pst.setString(1, customer.id);
            this.pst.executeUpdate();
            this.pst.close();
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean isNotUsed(Customer customer) {
        this.con = this.dbCon.getConnection();
        boolean isNotUsed = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Sells where CustomerId=?");
            this.pst.setString(1, customer.id);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("ERROE : Already exist ");
                alert.setContentText("This Customer use in sell'" + this.rs.getString(2) + "' brand \n delete Customer first");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return isNotUsed;
            }
            this.rs.close();
            this.pst.close();
            this.con.close();
            isNotUsed = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isNotUsed;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.getway\CustomerGetway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */