package com.exuberant.ims.bll;
import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.getway.CustomerGetway;
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
public class CustomerBLL {
    SQL sql = new SQL();
    CustomerGetway customerGetway = new CustomerGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    public void save(Customer customer) {
        if (isUniqName(customer)) {
            this.customerGetway.save(customer);
        }
    }
    public void update(Customer customer) {
        if (isUpdate(customer)) {
            if (isSame(customer)) {
                this.customerGetway.update(customer);
            } else if (isUniqName(customer)) {
                this.customerGetway.update(customer);
            }
        }
    }
    public boolean isUniqName(Customer customer) {
        boolean inUniqName = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer where CustomerName=? and CustomerContNo=?");
            this.pst.setString(1, customer.customerName);
            this.pst.setString(2, customer.customerConNo);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : used");
                alert.setContentText("This Customer name or phone number already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                return inUniqName;
            }
            inUniqName = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inUniqName;
    }
    public boolean isUpdate(Customer customer) {
        boolean isUpdate = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer where Id=? and CustomerName=? and CustomerContNo=? and CustomerAddress=?");
            this.pst.setString(1, customer.id);
            this.pst.setString(2, customer.customerName);
            this.pst.setString(3, customer.customerConNo);
            this.pst.setString(4, customer.customerAddress);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    private boolean isSame(Customer customer) {
        boolean isSame = false;
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Customer where id=? and CustomerName=? and CustomerContNo=?");
            this.pst.setString(1, customer.id);
            this.pst.setString(2, customer.customerName);
            this.pst.setString(3, customer.customerConNo);
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                return isSame;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSame;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.bll\CustomerBLL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */