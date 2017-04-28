package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.UnitBLL;
import com.exuberant.ims.dal.*;
import com.exuberant.ims.getway.UnitGetway;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;

public class CurrentStockController
        implements Initializable {
    Unit unit = Unit.GRAMS;
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();
    private Long userId;
    private String creatorId;
    private String unitId;
    private UserNameMedia media;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ProductGui> tblCurrentStock;
    @FXML
    private TableColumn<Object, Object> clmUnitId;
    @FXML
    private TableColumn clmDescription;
    @FXML
    private TableColumn<Stock, BigDecimal> clmMrp;
    @FXML
    private TableColumn<Object, Object> clmCostOfSelling;
    @FXML
    private TableColumn<Object, Object> clmQuantity;
    @FXML
    private TableColumn<Object, Object> clmWeight;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;

    public UserNameMedia getMedia() {
        return this.media;
    }

    public void setMedia(UserNameMedia media) {
        this.userId = media.getId();
        this.media = media;
    }

    public void initialize(URL url, ResourceBundle rb) {
        clmDescription.setCellValueFactory(new PropertyValueFactory("description"));
        clmMrp.setCellValueFactory(new PropertyValueFactory("mrp"));
        clmCostOfSelling.setCellValueFactory(new PropertyValueFactory("costOfSelling"));
        clmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        clmWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        tblCurrentStock.setItems(fetchCurrentStock());
    }

    private ObservableList<ProductGui> fetchCurrentStock() {
        Product p1 = new Product("Desc 1", new BigDecimal(1.0), new BigDecimal(10.0), new BigDecimal(50.0), new BigDecimal(2.0), new BigDecimal(15.0));
        Product p2 = new Product("Desc 2", new BigDecimal(1.0), new BigDecimal(10.0), new BigDecimal(50.0), new BigDecimal(2.0), new BigDecimal(15.0));
        Product p3 = new Product("Desc 3", new BigDecimal(1.0), new BigDecimal(10.0), new BigDecimal(50.0), new BigDecimal(2.0), new BigDecimal(15.0));
        Collection<ProductGui> products = Arrays.asList(new ProductGui(p1),new ProductGui(p2),new ProductGui(p3));
        return FXCollections.observableArrayList(products);
    }

    @FXML
    private void tblViewStockOnClick(MouseEvent event) {
        viewDetails();
    }

    @FXML
    private void btnAddItemOnAction(ActionEvent event) {
        AddUnitController addUnitController = new AddUnitController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(URLService.getFileAsResoure("application/stock/AddUnit.fxml"));
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

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (this.tblCurrentStock.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!this.tblCurrentStock.getSelectionModel().isEmpty()) {
            ProductGui selectedProduct = (ProductGui) this.tblCurrentStock.getSelectionModel().getSelectedItem();
            String unitName = selectedProduct.getDescription();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                this.unitBLL.delete(this.unit);
                tfSearchOnKeyResele(event);
            }
        } else {
            System.out.println("NULL SELECTED");
        }
    }

    @FXML
    private void miSearchOnAction(ActionEvent event) {
        this.tfSearch.requestFocus();
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddItemOnAction(event);
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        miUpdateOnAction(event);
    }

    public void showDetails() {
        this.clmUnitId.setCellValueFactory(new PropertyValueFactory("unitId"));
//        this.clmDescription.setCellValueFactory(new PropertyValueFactory("unitName"));
        this.clmMrp.setCellValueFactory(new PropertyValueFactory("unitDescription"));
        this.clmCostOfSelling.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmQuantity.setCellValueFactory(new PropertyValueFactory("dateOfCreation"));
        this.unitGetway.view(this.unit);
    }

    private void viewDetails() {
        if (!this.tblCurrentStock.getSelectionModel().isEmpty()) {
            ProductGui selectedProduct = (ProductGui) this.tblCurrentStock.getSelectionModel().getSelectedItem();
            ProductId productId = null;
            if (productId!=null) {
                AddUnitController addUnitController = new AddUnitController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(URLService.getFileAsResoure("application/stock/AddUnit.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = (Parent) fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    AddUnitController unitController = (AddUnitController) fxmlLoader.getController();
                    media.setId(this.userId);
                    unitController.setNameMedia(media);
                    unitController.lblContent.setText("UNIT DETAILS");
                    unitController.btnUpdate.setVisible(true);
                    unitController.btnSave.setVisible(true);
                    unitController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("empty Selection");
        }
    }

    @FXML
    public void tfSearchOnKeyResele(Event event) {
        this.unit = Unit.valueOf(this.tfSearch.getText().trim());
        this.clmUnitId.setCellValueFactory(new PropertyValueFactory("unitId"));
        //this.clmDescription.setCellValueFactory(new PropertyValueFactory("unitName"));
        this.clmMrp.setCellValueFactory(new PropertyValueFactory("unitDescription"));
        this.clmCostOfSelling.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmQuantity.setCellValueFactory(new PropertyValueFactory("dateOfCreation"));
        this.unitGetway.searchView(this.unit);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        showDetails();
    }
}
