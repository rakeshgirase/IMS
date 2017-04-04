package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.CurrentProductBLL;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.SQL;
import com.exuberant.ims.getway.CurrentProductGetway;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class AddProductController
        implements Initializable {
    public ComboBox<String> cmbBrand;
    public ComboBox cmbCatagory;
    public Button btnAddSupplier;
    public Button btnAddBrand;
    public Button btnAddCatagory;
    public Button btnAddUnit;
    public Button btnAddRma;
    @FXML
    public Button btnSave;
    public String id;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeader;
    CurrentProduct currentProduct = new CurrentProduct();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");
    @FXML
    private RadioButton rbStatic;
    @FXML
    private RadioButton rbSeq;
    private String usrId;
    private UserNameMedia nameMedia;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfProductId;
    @FXML
    private TextField tfProductName;
    @FXML
    private TextField tfProductQuantity;
    @FXML
    private TextField tfProductPursesPrice;
    @FXML
    private TextField tfProductSellPrice;
    @FXML
    private TextArea taProductDescription;
    @FXML
    private ComboBox<String> cbUnit;
    @FXML
    private ComboBox<String> cbRMA;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfValue;
    private String supplyerName;
    private String supplyerId;
    private String brandName;
    private String brandId;
    private String catagoryName;
    private String catagoryId;
    private String unitId;
    private String rmaId;
    @FXML
    private ComboBox<String> cbSupplyer;
    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }
    public void setNameMedia(UserNameMedia nameMedia) {
        this.usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup firstRedioBtn = new ToggleGroup();
        this.rbSeq.setToggleGroup(firstRedioBtn);
        this.rbStatic.setSelected(true);
        this.rbStatic.setToggleGroup(firstRedioBtn);
        this.tfValue.setVisible(false);
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println("Presesd");
        if (isNotNull()) {
            this.unitId = this.sql.getIdNo((String) this.cbUnit.getSelectionModel().getSelectedItem(), this.unitId, "Unit", "UnitName");
            this.rmaId = this.sql.getIdNo((String) this.cbRMA.getSelectionModel().getSelectedItem(), this.rmaId, "RMA", "RMAName");
            if (!this.tfValue.getText().trim().isEmpty()) {
                String value = this.tfValue.getText();
                int foo = Integer.parseInt(value);
                for (int i = 1; i <= foo; i++) {
                    this.currentProduct.productId = (this.tfProductId.getText().trim() + i);
                    this.currentProduct.productName = this.tfProductName.getText().trim();
                    this.currentProduct.quantity = this.tfProductQuantity.getText().trim();
                    this.currentProduct.pursesPrice = this.tfProductPursesPrice.getText().trim();
                    this.currentProduct.sellPrice = this.tfProductSellPrice.getText().trim();
                    this.currentProduct.description = this.taProductDescription.getText().trim();
                    this.currentProduct.supplierId = this.supplyerId;
                    this.currentProduct.brandId = this.brandId;
                    this.currentProduct.catagoryId = this.catagoryId;
                    this.currentProduct.unitId = this.unitId;
                    this.currentProduct.rmaId = this.rmaId;
                    this.currentProduct.userId = this.usrId;
                    this.currentProduct.date = ((LocalDate) this.dpDate.getValue()).toString();
                    this.currentProductBLL.save(this.currentProduct);
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Product added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            } else {
                this.currentProduct.productId = this.tfProductId.getText().trim();
                this.currentProduct.productName = this.tfProductName.getText().trim();
                this.currentProduct.quantity = this.tfProductQuantity.getText().trim();
                this.currentProduct.pursesPrice = this.tfProductPursesPrice.getText().trim();
                this.currentProduct.sellPrice = this.tfProductSellPrice.getText().trim();
                this.currentProduct.description = this.taProductDescription.getText().trim();
                this.currentProduct.supplierId = this.supplyerId;
                this.currentProduct.brandId = this.brandId;
                this.currentProduct.catagoryId = this.catagoryId;
                this.currentProduct.unitId = this.unitId;
                this.currentProduct.rmaId = this.rmaId;
                this.currentProduct.userId = this.usrId;
                this.currentProduct.date = ((LocalDate) this.dpDate.getValue()).toString();
                this.currentProductBLL.save(this.currentProduct);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Product added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            }
        }
    }
    @FXML
    private void rbSeqOnClick(MouseEvent event) {
        if (this.rbSeq.isSelected()) {
            this.tfValue.setVisible(true);
        } else if (!this.rbSeq.isSelected()) {
            this.tfValue.setVisible(false);
        }
    }
    @FXML
    private void rbSeqOnAction(ActionEvent event) {
    }
    @FXML
    private void rbStaticOnClicked(MouseEvent event) {
        if (this.rbStatic.isSelected()) {
            this.tfValue.setVisible(false);
            this.tfValue.clear();
        } else if (!this.rbStatic.isSelected()) {
            this.tfValue.setVisible(true);
        }
    }
    @FXML
    private void rbStaticOnAction(ActionEvent event) {
    }
    @FXML
    private void cbSupplyerOnClicked(MouseEvent event) {
        this.cbSupplyer.getSelectionModel().clearSelection();
        this.cbSupplyer.getItems().clear();
        this.cmbBrand.getSelectionModel().clearSelection();
        this.cmbBrand.getItems().clear();
        this.cmbBrand.getItems().removeAll(new String[0]);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbSupplyer.getItems().addAll(new String[]{this.rs.getString(2)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cbSupplyerOnAction(ActionEvent event) {
        this.cbSupplyer.getSelectionModel().getSelectedItem();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer where SupplyerName=?");
            this.pst.setString(1, (String) this.cbSupplyer.getSelectionModel().getSelectedItem());
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.supplyerId = this.rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void cmbBrandOnClick(Event event) {
        this.cmbBrand.getItems().clear();
        this.cmbCatagory.getItems().clear();
        this.cmbCatagory.getItems().removeAll(new Object[0]);
        this.cmbCatagory.setPromptText(null);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where SupplyerId=?");
            this.pst.setString(1, this.supplyerId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cmbBrand.getItems().addAll(new String[]{this.rs.getString(2)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void cmbCatagoryOnClick(Event event) {
        this.cmbCatagory.getItems().clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where SupplyerId=? and BrandId=?");
            this.pst.setString(1, this.supplyerId);
            this.pst.setString(2, this.brandId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cmbCatagory.getItems().addAll(new Object[]{this.rs.getString(2)});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cmbBrandOnAction(ActionEvent event) {
        this.cmbBrand.getSelectionModel().getSelectedItem();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where BrandName=? and SupplyerId=?");
            this.pst.setString(1, (String) this.cmbBrand.getSelectionModel().getSelectedItem());
            this.pst.setString(2, this.supplyerId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.brandId = this.rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void cmbCatagoryOnAction(ActionEvent actionEvent) {
        this.cmbCatagory.getSelectionModel().getSelectedItem();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Catagory where SupplyerId=? and BrandId=?");
            this.pst.setString(1, this.supplyerId);
            this.pst.setString(2, this.brandId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.catagoryId = this.rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cbUnitOnClick(MouseEvent event) {
        this.cbUnit.getItems().clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Unit");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbUnit.getItems().addAll(new String[]{this.rs.getString(2)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cbRMAOnClick(MouseEvent event) {
        this.cbRMA.getItems().clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".RMA");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbRMA.getItems().addAll(new String[]{this.rs.getString(2)});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean isNotNull() {
        boolean insNotNull = false;
        if (((this.cbSupplyer.getSelectionModel().getSelectedItem() == null) &&
                (this.cbSupplyer.getPromptText().isEmpty())) ||
                ((this.cmbBrand.getSelectionModel().getSelectedItem() == null) &&
                        (this.cmbBrand.getPromptText().isEmpty())) ||
                ((this.cmbCatagory.getSelectionModel().isEmpty()) &&
                        (this.cmbCatagory.getPromptText().isEmpty())) ||
                (this.tfProductId.getText().isEmpty()) ||
                (this.tfProductName.getText().isEmpty()) ||
                (this.tfProductQuantity.getText().isEmpty()) ||
                (this.tfProductPursesPrice.getText().isEmpty())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("ERROR : NULL FOUND");
            alert.setContentText("Please fill all require field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            insNotNull = false;
        } else {
            insNotNull = true;
        }
        return insNotNull;
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (isNotNull()) {
            System.out.println(this.supplyerId + this.brandId + this.brandId + this.unitId + this.rmaId + this.usrId);
            this.currentProduct.productId = this.tfProductId.getText();
            this.currentProduct.productName = this.tfProductName.getText();
            this.currentProduct.quantity = this.tfProductQuantity.getText();
            this.currentProduct.pursesPrice = this.tfProductPursesPrice.getText();
            this.currentProduct.sellPrice = this.tfProductSellPrice.getText();
            this.currentProduct.supplierId = this.supplyerId;
            this.currentProduct.brandId = this.brandId;
            this.currentProduct.catagoryId = this.catagoryId;
            this.currentProduct.unitId = this.unitId;
            this.currentProduct.rmaId = this.rmaId;
            this.currentProduct.id = this.id;
            this.currentProductBLL.update(this.currentProduct);
            refreshProductList();
        }
    }
    public void viewSelected() {
        this.currentProduct.id = this.id;
        this.currentProductGetway.selectedView(this.currentProduct);
        this.tfProductId.setText(this.currentProduct.productId);
        this.tfProductName.setText(this.currentProduct.productName);
        this.tfProductQuantity.setText(this.currentProduct.quantity);
        this.tfProductPursesPrice.setText(this.currentProduct.pursesPrice);
        this.tfProductSellPrice.setText(this.currentProduct.sellPrice);
        this.taProductDescription.setText(this.currentProduct.description);
        this.dpDate.setValue(LocalDate.parse(this.currentProduct.date));
        this.supplyerId = this.currentProduct.supplierId;
        this.brandId = this.currentProduct.brandId;
        this.catagoryId = this.currentProduct.catagoryId;
        this.unitId = this.currentProduct.unitId;
        this.rmaId = this.currentProduct.rmaId;
        this.cbSupplyer.setPromptText(this.currentProduct.supplierName);
        this.cmbBrand.setPromptText(this.currentProduct.brandName);
        this.cmbCatagory.setPromptText(this.currentProduct.catagoryName);
        this.cbUnit.setPromptText(this.currentProduct.unitName);
        this.cbRMA.setPromptText(this.currentProduct.rmaName);
    }
    @FXML
    private void cbUnitOnAction(ActionEvent event) {
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Unit where UnitName=?");
            this.pst.setString(1, (String) this.cbUnit.getSelectionModel().getSelectedItem());
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.unitId = this.rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cbRMAOnAction(ActionEvent event) {
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".RMA where RMAName=?");
            this.pst.setString(1, (String) this.cbRMA.getSelectionModel().getSelectedItem());
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.rmaId = this.rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
        AddSupplyerController addSupplyerController = new AddSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddSupplier.fxml");
        fxmlLoader.setLocation(resource);
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddSupplyerController supplyerController = (AddSupplyerController) fxmlLoader.getController();
            media.setId(this.usrId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Supplyer");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            supplyerController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnAddBrandOnAction(ActionEvent actionEvent) {
        AddBrandController addSupplyerController = new AddBrandController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddBrand.fxml");
        fxmlLoader.setLocation(resource);
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddBrandController supplyerController = (AddBrandController) fxmlLoader.getController();
            media.setId(this.usrId);
            supplyerController.setMedia(media);
            supplyerController.lblHeader.setText("Add Brand");
            supplyerController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnAddCatagoryOnAction(ActionEvent actionEvent) {
        AddCatagoryController addCatagoryController = new AddCatagoryController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddCategory.fxml");
        fxmlLoader.setLocation(resource);
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddCatagoryController catagoryController = (AddCatagoryController) fxmlLoader.getController();
            media.setId(this.usrId);
            catagoryController.setMedia(media);
            catagoryController.lblHeaderContent.setText("Add Item");
            catagoryController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnAddUnitOnAction(ActionEvent actionEvent) {
        AddUnitController addUnitController = new AddUnitController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddUnit.fxml");
        fxmlLoader.setLocation(resource);
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddUnitController unitController = (AddUnitController) fxmlLoader.getController();
            media.setId(this.usrId);
            unitController.setNameMedia(media);
            unitController.lblContent.setText("ADD UNIT");
            unitController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnAddRmaOnAction(ActionEvent actionEvent) {
        AddRMAController addRMAController = new AddRMAController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddRMA.fxml");
        fxmlLoader.setLocation(resource);
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddRMAController rmaController = (AddRMAController) fxmlLoader.getController();
            media.setId(this.usrId);
            rmaController.setMedia(media);
            rmaController.lblContent.setText("ADD RMA");
            rmaController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void refreshProductList() {
        try {
            CurrentStoreController asc = new CurrentStoreController();
            FXMLLoader fXMLLoader = new FXMLLoader();
            URL resource = URLService.getFileAsResoure("application/stock/CurrentStore.fxml");
            fXMLLoader.load(resource.openStream());
            CurrentStoreController currentStoreController = (CurrentStoreController) fXMLLoader.getController();
            currentStoreController.viewDetails();
        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
