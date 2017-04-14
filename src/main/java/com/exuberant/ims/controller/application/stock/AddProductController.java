package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.CurrentProductBLL;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.dal.Supplier;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.gateway.HibernateRepository;
import com.exuberant.ims.getway.CurrentProductGetway;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
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
import java.time.LocalDate;
import java.util.List;
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

    @FXML
    private RadioButton rbStatic;
    @FXML
    private RadioButton rbSeq;
    private Long userId;
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
        this.userId = nameMedia.getId();
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
        if (!this.tfValue.getText().trim().isEmpty()) {
            String value = this.tfValue.getText();
            int foo = Integer.parseInt(value);
            for (int i = 1; i <= foo; i++) {
                this.currentProduct.setProductId(this.tfProductId.getText().trim() + i);
                this.currentProduct.setProductName(this.tfProductName.getText().trim());
                this.currentProduct.setQuantity(this.tfProductQuantity.getText().trim());
                this.currentProduct.setPursesPrice(this.tfProductPursesPrice.getText().trim());
                this.currentProduct.setSellPrice(this.tfProductSellPrice.getText().trim());
                this.currentProduct.setDescription(this.taProductDescription.getText().trim());
                this.currentProduct.setSupplierName(this.supplyerId);
                this.currentProduct.setBrandId(this.brandId);
                this.currentProduct.setCatagoryId(this.catagoryId);
                this.currentProduct.setUnitId(this.unitId);
                this.currentProduct.setRmaId(this.rmaId);
                this.currentProduct.setUserId(this.userId);
                this.currentProduct.setDate(this.dpDate.getValue().toString());
                HibernateRepository.getRepository().save(this.currentProduct);
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("error");
            alert.setHeaderText("Sucess : save sucess ");
            alert.setContentText("Product added successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
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
        List<Supplier> suppliers = HibernateRepository.getRepository().getAll(Supplier.class);
        suppliers.forEach(supplier -> this.cbSupplyer.getItems().add(supplier.supplierName));
    }

    @FXML
    private void cbSupplyerOnAction(ActionEvent event) {
        this.cbSupplyer.getSelectionModel().getSelectedItem();
        this.supplyerId = "TEST SUPPLIER";
    }

    @FXML
    public void cmbBrandOnClick(Event event) {
        this.cmbBrand.getItems().clear();
        this.cmbCatagory.getItems().clear();
        this.cmbCatagory.getItems().removeAll(new Object[0]);
        this.cmbCatagory.setPromptText(null);
        this.cmbBrand.getItems().addAll("TEST BRAND");
    }

    @FXML
    public void cmbCatagoryOnClick(Event event) {
        this.cmbCatagory.getItems().clear();
        this.cmbCatagory.getItems().addAll(new Object[]{"Add Categories"});
    }

    @FXML
    private void cmbBrandOnAction(ActionEvent event) {
        this.cmbBrand.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void cmbCatagoryOnAction(ActionEvent event) {
        this.cmbCatagory.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void cbUnitOnClick(MouseEvent event) {
        this.cbUnit.getItems().clear();
        List<Unit> units = HibernateRepository.getRepository().getAll(Unit.class);
        units.forEach(unit -> this.cbUnit.getItems().add(unit.unitName));
    }

    @FXML
    private void cbRMAOnClick(MouseEvent event) {
        this.cbRMA.getItems().clear();
        List<RMA> rmas = HibernateRepository.getRepository().getAll(RMA.class);
        rmas.forEach(rma -> this.cbRMA.getItems().add(rma.rmaName));
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
            System.out.println(this.supplyerId + this.brandId + this.brandId + this.unitId + this.rmaId + this.userId);
            this.currentProductBLL.update(this.currentProduct);
            this.currentProduct.setProductId(this.tfProductId.getText().trim());
            this.currentProduct.setProductName(this.tfProductName.getText().trim());
            this.currentProduct.setQuantity(this.tfProductQuantity.getText().trim());
            this.currentProduct.setPursesPrice(this.tfProductPursesPrice.getText().trim());
            this.currentProduct.setSellPrice(this.tfProductSellPrice.getText().trim());
            this.currentProduct.setDescription(this.taProductDescription.getText().trim());
            this.currentProduct.setSupplierName(this.supplyerId);
            this.currentProduct.setBrandId(this.brandId);
            this.currentProduct.setCatagoryId(this.catagoryId);
            this.currentProduct.setUnitId(this.unitId);
            this.currentProduct.setRmaId(this.rmaId);
            this.currentProduct.setUserId(this.userId);
            this.currentProduct.setDate(this.dpDate.getValue().toString());
            HibernateRepository.getRepository().save(this.currentProduct);
            refreshProductList();
        }
    }

    public void viewSelected() {
        this.currentProduct.setId(this.id);
        this.currentProductGetway.selectedView(this.currentProduct);
        this.tfProductId.setText(this.currentProduct.getProductId());
        this.tfProductName.setText(this.currentProduct.getProductName());
        this.tfProductQuantity.setText(this.currentProduct.getQuantity());
        this.tfProductPursesPrice.setText(this.currentProduct.getPursesPrice());
        this.tfProductSellPrice.setText(this.currentProduct.getSellPrice());
        this.taProductDescription.setText(this.currentProduct.getDescription());
        this.dpDate.setValue(LocalDate.parse(this.currentProduct.getDate()));
        this.supplyerId = this.currentProduct.getSupplierId();
        this.brandId = this.currentProduct.getBrandId();
        this.catagoryId = this.currentProduct.getCatagoryId();
        this.unitId = this.currentProduct.getUnitId();
        this.rmaId = this.currentProduct.getRmaId();
        this.cbSupplyer.setPromptText(this.currentProduct.getSupplierName());
        this.cmbBrand.setPromptText(this.currentProduct.getBrandName());
        this.cmbCatagory.setPromptText(this.currentProduct.getCatagoryName());
        this.cbUnit.setPromptText(this.currentProduct.getUnitName());
        this.cbRMA.setPromptText(this.currentProduct.getRmaName());
    }

    @FXML
    private void cbUnitOnAction(ActionEvent event) {
        HibernateRepository.getRepository().get(Unit.class, 1L);
    }

    @FXML
    private void cbRMAOnAction(ActionEvent event) {
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
            media.setId(this.userId);
            supplyerController.setMedia(media);
            supplyerController.lblCaption.setText("Add Supplier");
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
            media.setId(this.userId);
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
            media.setId(this.userId);
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
            media.setId(this.userId);
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
            media.setId(this.userId);
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
