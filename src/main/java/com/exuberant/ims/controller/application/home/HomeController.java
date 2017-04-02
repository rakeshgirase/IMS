package com.exuberant.ims.controller.application.home;
import com.exuberant.ims.getway.CurrentProductGetway;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.util.PropertyService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class HomeController
        implements Initializable {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String db = PropertyService.getInstance().getProperty("db");
    @FXML
    private Label lblTotalItem;
    @FXML
    private Label lblStockValue;
    @FXML
    private Label lblTotalSupplyer;
    @FXML
    private Label lblTotalEmployee;
    @FXML
    private Label lblTotalSell;
    @FXML
    private Label lblSellValue;
    @FXML
    private Label lblOrgName;
    @FXML
    private Text txtOrgAddress;
    @FXML
    private Text txtorgPhoneNumber;
    public void initialize(URL url, ResourceBundle rb) {
        valueCount();
        totalCount();
    }
    public void valueCount() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select sum(PursesPrice) from " + this.db + ".Products");
            this.rs = this.pst.executeQuery();
            while (this.rs.next())
                this.lblStockValue.setText(this.rs.getString(1));
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void totalCount() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select count(*) from " + this.db + ".Sell");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblTotalSell.setText(this.rs.getString(1));
            }
            this.pst = this.con.prepareStatement("select count(*) from " + this.db + ".Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblTotalSupplyer.setText(this.rs.getString(1));
            }
            this.pst = this.con.prepareStatement("select count(*) from " + this.db + ".Products");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblTotalItem.setText(this.rs.getString(1));
            }
            this.pst = this.con.prepareStatement("select sum(TotalPrice) from " + this.db + ".Sell");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblSellValue.setText(this.rs.getString(1));
            }
            this.pst = this.con.prepareStatement("select count(*) from " + this.db + ".User");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblTotalEmployee.setText(this.rs.getString(1));
            }
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Organize where Id=1");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.lblOrgName.setText(this.rs.getString(2));
                this.txtOrgAddress.setText(this.rs.getString(5));
                this.txtorgPhoneNumber.setText(this.rs.getString(4));
            }
            this.con.close();
            this.pst.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentProductGetway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
