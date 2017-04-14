package com.exuberant.ims.controller.application.employee;

import com.exuberant.ims.media.UserNameMedia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeePermissionController
        implements Initializable {

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
    private Long id;
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
