package com.exuberant.ims.controller.application.employe;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EmployeePermissionController
        implements Initializable {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    @FXML
    private CheckBox cbStockManegement;
    @FXML
    private CheckBox cbAddProduct;
    @FXML
    private CheckBox cbAddSupplyer;
    @FXML
    private CheckBox cbAddBrand;
    @FXML
    private CheckBox cbAddCatagory;
    @FXML
    private CheckBox cbUpdateProduct;
    @FXML
    private CheckBox cbUpdateSupplyert;
    @FXML
    private CheckBox cbUpdateBrands;
    @FXML
    private CheckBox cbUpdateCatagory;
    @FXML
    private CheckBox cbSellManegement;
    @FXML
    private CheckBox cbSellProduct;
    @FXML
    private CheckBox cbAddCustomer;
    @FXML
    private CheckBox cbUpdateCustomer;
    @FXML
    private CheckBox cbEmployeeManegement;
    @FXML
    private CheckBox cbAddEmployee;
    @FXML
    private CheckBox cbUpdateEmployee;
    @FXML
    private CheckBox cbEmployePassChange;
    @FXML
    private CheckBox cbUser;
    @FXML
    private CheckBox cbChangePassword;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnClose;
    private String id;
    private UserNameMedia media;
    @FXML
    private CheckBox cbAddUnit;
    @FXML
    private CheckBox cbUpdateUnit;
    @FXML
    private CheckBox cbProvideDiscount;
    @FXML
    private CheckBox cbOrgManage;
    @FXML
    private CheckBox cbMenageRMA;
    public UserNameMedia getMedia() {
        return this.media;
    }
    public void setMedia(UserNameMedia media) {
        this.id = media.getId();
        this.media = media;
    }
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        int addProduct = this.cbAddProduct.isSelected() ? 1 : 0;
        int addSupplyer = this.cbAddSupplyer.isSelected() ? 1 : 0;
        int addBrand = this.cbAddBrand.isSelected() ? 1 : 0;
        int AddCatagory = this.cbAddCatagory.isSelected() ? 1 : 0;
        int AddUnit = this.cbAddUnit.isSelected() ? 1 : 0;
        int AddCustomer = this.cbAddCustomer.isSelected() ? 1 : 0;
        int UpdateProduct = this.cbUpdateProduct.isSelected() ? 1 : 0;
        int UpdateSupplyer = this.cbUpdateSupplyert.isSelected() ? 1 : 0;
        int UpdateBrand = this.cbUpdateBrands.isSelected() ? 1 : 0;
        int UpdateCatagory = this.cbUpdateCatagory.isSelected() ? 1 : 0;
        int UpdateUnit = this.cbUpdateUnit.isSelected() ? 1 : 0;
        int UpdateCustomer = this.cbUpdateCustomer.isSelected() ? 1 : 0;
        int SellProduct = this.cbSellProduct.isSelected() ? 1 : 0;
        int ProvideDiscount = this.cbProvideDiscount.isSelected() ? 1 : 0;
        int EmployeManage = this.cbEmployeeManegement.isSelected() ? 1 : 0;
        int OrgManage = this.cbOrgManage.isSelected() ? 1 : 0;
        int ChangeOwnPass = this.cbChangePassword.isSelected() ? 1 : 0;
        int menageRMA = this.cbMenageRMA.isSelected() ? 1 : 0;
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("UPDATE " + this.db + ".UserPermission SET AddProduct=?,AddSupplyer=?,AddBrand=?,AddCatagory=?,AddUnit=?,AddCustomer=?,UpdateProduct=?,UpdateSupplyer=?,UpdateBrand=?,UpdateCatagory=?,UpdateUnit=?,UpdateCustomer=?,SellProduct=?,ProvideDiscount=?,EmployeManage=?,OrgManage=?,ChangeOwnPass=?,RMAManage=? WHERE UserId=?");
            this.pst.setInt(1, addProduct);
            this.pst.setInt(2, addSupplyer);
            this.pst.setInt(3, addBrand);
            this.pst.setInt(4, AddCatagory);
            this.pst.setInt(5, AddUnit);
            this.pst.setInt(6, AddCustomer);
            this.pst.setInt(7, UpdateProduct);
            this.pst.setInt(8, UpdateSupplyer);
            this.pst.setInt(9, UpdateBrand);
            this.pst.setInt(10, UpdateCatagory);
            this.pst.setInt(11, UpdateUnit);
            this.pst.setInt(12, UpdateCustomer);
            this.pst.setInt(13, SellProduct);
            this.pst.setInt(14, ProvideDiscount);
            this.pst.setInt(15, EmployeManage);
            this.pst.setInt(16, OrgManage);
            this.pst.setInt(17, ChangeOwnPass);
            this.pst.setInt(18, menageRMA);
            this.pst.setString(19, this.id);
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess");
            alert.setContentText("Permission change successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnCloseOnClick(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cbStockOnAction(ActionEvent event) {
        stockManeCbOperation();
    }
    @FXML
    private void cbSellManegementOnAction(ActionEvent event) {
    }
    @FXML
    private void cbEmployeeManegementOnAction(ActionEvent event) {
        if (this.cbEmployeeManegement.isSelected()) {
            this.cbAddEmployee.setSelected(true);
            this.cbEmployePassChange.setSelected(true);
            this.cbUpdateEmployee.setSelected(true);
        } else if (!this.cbEmployeeManegement.isSelected()) {
            this.cbAddEmployee.setSelected(true);
            this.cbEmployePassChange.setSelected(true);
            this.cbUpdateEmployee.setSelected(true);
        }
    }
    @FXML
    private void cbUserOnAction(ActionEvent event) {
    }
    public void checqPermission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where Id=?");
            this.pst.setString(1, this.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt(2) == 1) {
                    this.cbAddProduct.setSelected(true);
                } else {
                    this.cbAddProduct.setSelected(false);
                }
                if (this.rs.getInt(3) == 1) {
                    this.cbAddSupplyer.setSelected(true);
                } else {
                    this.cbAddSupplyer.setSelected(false);
                }
                if (this.rs.getInt(4) == 1) {
                    this.cbAddBrand.setSelected(true);
                } else {
                    this.cbAddBrand.setSelected(false);
                }
                if (this.rs.getInt(5) == 1) {
                    this.cbAddCatagory.setSelected(true);
                } else {
                    this.cbAddCatagory.setSelected(false);
                }
                if (this.rs.getInt(6) == 1) {
                    this.cbAddUnit.setSelected(true);
                } else {
                    this.cbAddUnit.setSelected(false);
                }
                if (this.rs.getInt(7) == 1) {
                    this.cbAddCustomer.setSelected(true);
                } else {
                    this.cbAddCustomer.setSelected(false);
                }
                if (this.rs.getInt(8) == 1) {
                    this.cbUpdateProduct.setSelected(true);
                } else {
                    this.cbUpdateProduct.setSelected(false);
                }
                if (this.rs.getInt(9) == 1) {
                    this.cbUpdateSupplyert.setSelected(true);
                } else {
                    this.cbUpdateSupplyert.setSelected(false);
                }
                if (this.rs.getInt(10) == 1) {
                    this.cbUpdateBrands.setSelected(true);
                } else {
                    this.cbUpdateBrands.setSelected(false);
                }
                if (this.rs.getInt(11) == 1) {
                    this.cbUpdateCatagory.setSelected(true);
                } else {
                    this.cbUpdateCatagory.setSelected(false);
                }
                if (this.rs.getInt(12) == 1) {
                    this.cbUpdateUnit.setSelected(true);
                } else {
                    this.cbUpdateUnit.setSelected(false);
                }
                if (this.rs.getInt(13) == 1) {
                    this.cbUpdateCustomer.setSelected(true);
                } else {
                    this.cbUpdateCustomer.setSelected(false);
                }
                if (this.rs.getInt(14) == 1) {
                    this.cbMenageRMA.setSelected(true);
                } else {
                    this.cbMenageRMA.setSelected(false);
                }
                if (this.rs.getInt(15) == 1) {
                    this.cbSellProduct.setSelected(true);
                } else {
                    this.cbSellProduct.setSelected(false);
                }
                if (this.rs.getInt(16) == 1) {
                    this.cbProvideDiscount.setSelected(true);
                } else {
                    this.cbProvideDiscount.setSelected(false);
                }
                if (this.rs.getInt(17) == 1) {
                    this.cbEmployeeManegement.setSelected(true);
                    this.cbAddEmployee.setSelected(true);
                    this.cbEmployePassChange.setSelected(true);
                    this.cbUpdateEmployee.setSelected(true);
                } else {
                    this.cbEmployeeManegement.setSelected(false);
                    this.cbAddEmployee.setSelected(false);
                    this.cbEmployePassChange.setSelected(false);
                    this.cbUpdateEmployee.setSelected(false);
                }
                if (this.rs.getInt(18) == 1) {
                    this.cbOrgManage.setSelected(true);
                } else {
                    this.cbOrgManage.setSelected(false);
                }
                if (this.rs.getInt(19) == 1) {
                    this.cbChangePassword.setSelected(true);
                } else {
                    this.cbChangePassword.setSelected(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeePermissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cbSotckMangeOnClick(MouseEvent event) {
    }
    private void stockManeCbOperation() {
        if (this.cbStockManegement.isSelected()) {
            this.cbAddProduct.setSelected(true);
            this.cbAddBrand.setSelected(true);
            this.cbAddCatagory.setSelected(true);
            this.cbAddSupplyer.setSelected(true);
            this.cbAddUnit.setSelected(true);
            this.cbUpdateBrands.setSelected(true);
            this.cbUpdateCatagory.setSelected(true);
            this.cbUpdateSupplyert.setSelected(true);
            this.cbUpdateProduct.setSelected(true);
            this.cbUpdateUnit.setSelected(true);
            this.cbMenageRMA.setSelected(true);
        } else if (!this.cbStockManegement.isSelected()) {
            this.cbAddProduct.setSelected(false);
            this.cbAddBrand.setSelected(false);
            this.cbAddCatagory.setSelected(false);
            this.cbAddSupplyer.setSelected(false);
            this.cbAddUnit.setSelected(false);
            this.cbUpdateBrands.setSelected(false);
            this.cbUpdateCatagory.setSelected(false);
            this.cbUpdateSupplyert.setSelected(false);
            this.cbUpdateProduct.setSelected(false);
            this.cbUpdateUnit.setSelected(false);
            this.cbMenageRMA.setSelected(false);
        }
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\employe\EmployeePermissionController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */