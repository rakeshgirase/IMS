package com.exuberant.ims.controller.application;
import com.exuberant.ims.controller.application.stock.*;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.util.PropertyService;
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
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StockController
        implements Initializable {
    @FXML
    public BorderPane bpStore;

    String db = PropertyService.getInstance().getProperty("db");
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private StackPane spMainContent;
    private String usrId;
    private UserNameMedia userId;
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
    public UserNameMedia getUserId() {
        return this.userId;
    }
    public void setUserId(UserNameMedia userId) {
        this.usrId = userId.getId();
        this.userId = userId;
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
        fXMLLoader.load(getClass().getResource("/view/application/stock/CurrentStore.fxml").openStream());
        media.setId(this.usrId);
        CurrentStoreController currentStoreController = (CurrentStoreController) fXMLLoader.getController();
        currentStoreController.setMedia(this.userId);
        currentStoreController.viewDetails();
        currentStoreController.apCombobox.getStylesheets().add("/style/StoreCombobox.css");
        currentStoreController.settingPermission();
        StackPane acPane = (StackPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnSupplyerOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Supplyer");
        ViewSupplyerController vsc = new ViewSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewSupplier.fxml").openStream());
        media.setId(this.usrId);
        ViewSupplyerController viewSupplyerController = (ViewSupplyerController) fXMLLoader.getController();
        viewSupplyerController.setMedia(this.userId);
        viewSupplyerController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnBrandsOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Brands");
        ViewBrandController vbc = new ViewBrandController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewBrand.fxml").openStream());
        media.setId(this.usrId);
        ViewBrandController viewBrandController = (ViewBrandController) fXMLLoader.getController();
        viewBrandController.setMedia(this.userId);
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
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewCategory.fxml").openStream());
        media.setId(this.usrId);
        ViewCatagoryController viewCatagoryController = (ViewCatagoryController) fXMLLoader.getController();
        viewCatagoryController.setMedia(this.userId);
        viewCatagoryController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnUnitOnAction(ActionEvent event) throws IOException {
        this.lblHeader.setText("Unit");
        ViewUnitController vuc = new ViewUnitController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewUnit.fxml").openStream());
        media.setId(this.usrId);
        ViewUnitController viewUnitController = (ViewUnitController) fXMLLoader.getController();
        viewUnitController.setMedia(this.userId);
        viewUnitController.showDetails();
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
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewRMA.fxml").openStream());
        media.setId(this.usrId);
        ViewRMAController viewRMAController = (ViewRMAController) fXMLLoader.getController();
        viewRMAController.setMedia(this.userId);
        viewRMAController.showDetails();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(acPane);
    }
    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }
    public void settingPermission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where id=?");
            this.pst.setString(1, this.usrId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if ((this.rs.getInt(2) == 0) && (this.rs.getInt(9) == 0))
                    this.btnSupplyer.setDisable(true);
                if ((this.rs.getInt(4) == 0) && (this.rs.getInt(10) == 0))
                    this.btnBrands.setDisable(true);
                if ((this.rs.getInt(5) == 0) && (this.rs.getInt(11) == 0))
                    this.btnCatagory.setDisable(true);
                if ((this.rs.getInt(6) == 0) && (this.rs.getInt(12) == 0))
                    this.btnUnit.setDisable(true);
                if (this.rs.getInt(14) == 0) {
                    this.btnRma.setDisable(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
