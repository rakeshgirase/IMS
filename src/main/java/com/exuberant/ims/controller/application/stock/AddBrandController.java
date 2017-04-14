package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.BrandBLL;
import com.exuberant.ims.dal.Brand;
import com.exuberant.ims.getway.BrandsGetway;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddBrandController
        implements Initializable {
    public Button btnAddSupplyer;
    public String brandId;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeader;
    @FXML
    public Button btnClose;
    @FXML
    public Button btnAddBrand;
    Brand brand = new Brand();
    BrandsGetway brandsGetway = new BrandsGetway();
    BrandBLL brandBLL = new BrandBLL();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");
    private UserNameMedia media;
    private Long userId;
    private String supplyerName;
    private String supplyerId;
    @FXML
    private ComboBox<String> cbSupplyer;
    @FXML
    private TextField tfBrandName;
    @FXML
    private TextArea taDiscription;

    public UserNameMedia getMedia() {
        return this.media;
    }

    public void setMedia(UserNameMedia media) {
        this.userId = media.getId();
        this.media = media;
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        System.out.println(this.userId);
        if (isNotNull()) {
            this.brand.creatorId = this.userId;
            this.brand.brandName = this.tfBrandName.getText();
            this.brand.brandComment = this.taDiscription.getText();
            this.brand.supplierName = ((String) this.cbSupplyer.getSelectionModel().getSelectedItem());
            this.brandBLL.save(this.brand);
        }
    }

    @FXML
    private void cbSupplyerOnAction(ActionEvent event) {
    }

    @FXML
    private void cbSupplyerOnClick(MouseEvent event) {
        this.cbSupplyer.getItems().clear();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer order by SupplyerName");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.supplyerName = this.rs.getString(2);
                this.cbSupplyer.getItems().add(this.supplyerName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBrandController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println();
        if (isNotNull()) {
            this.brand.id = this.brandId;
            if (!this.cbSupplyer.getSelectionModel().isEmpty()) {
                this.brand.supplierName = ((String) this.cbSupplyer.getSelectionModel().getSelectedItem());
            } else if (!this.cbSupplyer.getPromptText().isEmpty()) {
                this.brand.supplierName = this.cbSupplyer.getPromptText();
            }
            this.brand.brandName = this.tfBrandName.getText().trim();
            this.brand.brandComment = this.taDiscription.getText();
            this.brandBLL.update(this.brand);
        }
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }

    public boolean isNotNull() {
        System.out.println(this.cbSupplyer.getPromptText());
        System.out.println(this.tfBrandName.getText());
        boolean isNotNull;
        if ((this.tfBrandName.getText().trim().isEmpty()) || (
                (this.cbSupplyer.getSelectionModel().isEmpty()) &&
                        (this.cbSupplyer.getPromptText().isEmpty()))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("Error : null found ");
            alert.setContentText("Please full all requre field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    public void showDetails() {
        this.brand.id = this.brandId;
        this.brandsGetway.selectedView(this.brand);
        this.tfBrandName.setText(this.brand.brandName);
        this.taDiscription.setText(this.brand.brandComment);
        this.cbSupplyer.setPromptText(this.brand.supplierName);
    }

    public void btnAddSupplyerOnAction(ActionEvent actionEvent) {
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
            supplyerController.lblCaption.setText("Add Item");
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
}
