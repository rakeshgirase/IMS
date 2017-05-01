package com.exuberant.ims.controller.application;

import com.exuberant.ims.controller.application.stock.*;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StockController
        implements Initializable {
    @FXML
    public BorderPane bpStore;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private StackPane spMainContent;
    private UserNameMedia userNameMedia;
    private Long userId;
    @FXML
    private Label lblHeader;
    @FXML
    private ToggleButton btnStock;
    @FXML
    private ToggleButton btnSupplyer;
    @FXML
    private ToggleButton btnBrands;
    @FXML
    private ToggleButton btnCatagory;
    @FXML
    private ToggleButton btnUnit;
    @FXML
    private ToggleButton btnRma;
    @FXML
    private ToggleButton btnRepoerts;

    public void setUserId(UserNameMedia userId) {
        this.userId = userId.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup toggleGroup = new ToggleGroup();
        this.btnStock.setSelected(true);
        this.btnStock.setToggleGroup(toggleGroup);
        this.btnSupplyer.setToggleGroup(toggleGroup);
        this.btnBrands.setToggleGroup(toggleGroup);
        this.btnCatagory.setToggleGroup(toggleGroup);
        this.btnUnit.setToggleGroup(toggleGroup);
        this.btnRma.setToggleGroup(toggleGroup);
        this.btnRepoerts.setToggleGroup(toggleGroup);
    }

    @FXML
    public void btnStockOnAction(ActionEvent event)
            throws IOException {
        this.lblHeader.setText("Store");
        CurrentStoreController asc = new CurrentStoreController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/CurrentStock.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        CurrentStoreController currentStoreController = (CurrentStoreController) fXMLLoader.getController();
        currentStoreController.setMedia(media);
        currentStoreController.viewDetails();
        currentStoreController.apCombobox.getStylesheets().add("style/StoreCombobox.css");
        currentStoreController.settingPermission();
        StackPane acPane = (StackPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSupplyerOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Supplier");
        ViewSupplyerController vsc = new ViewSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/ViewSupplier.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewSupplyerController viewSupplyerController = (ViewSupplyerController) fXMLLoader.getController();
        viewSupplyerController.setMedia(media);
        viewSupplyerController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnBrandsOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Brand");
        ViewBrandController vbc = new ViewBrandController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/ViewBrand.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewBrandController viewBrandController = (ViewBrandController) fXMLLoader.getController();
        viewBrandController.setMedia(media);
        viewBrandController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCatagoryOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Catagories");
        ViewCatagoryController vcc = new ViewCatagoryController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/ViewCategory.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewCatagoryController viewCatagoryController = (ViewCatagoryController) fXMLLoader.getController();
        viewCatagoryController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnUnitOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Unit");
        CurrentStockController vuc = new CurrentStockController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/ViewUnit.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        CurrentStockController currentStockController = (CurrentStockController) fXMLLoader.getController();
        currentStockController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRmaOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("RMA");
        ViewRMAController vrmac = new ViewRMAController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/ViewRMA.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewRMAController viewRMAController = (ViewRMAController) fXMLLoader.getController();
        viewRMAController.setMedia(media);
        viewRMAController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }

    public void settingPermission() {

    }
}
