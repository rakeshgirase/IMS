package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.bll.CatagoryBLL;
import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.getway.CatagoryGetway;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.database.SQL;
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
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class AddCatagoryController
        implements Initializable {
    public String supplyerId;
    public String supplyerName;
    public String catagoryId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblHeaderContent;
    @FXML
    public Button btnClose;
    Catagory catagory = new Catagory();
    CatagoryGetway catagoryGetway = new CatagoryGetway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    SQL sql = new SQL();

    String db = PropertyService.getInstance().getProperty("db");
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    private String userId;
    private String brandId;
    private String brnadName;
    private UserNameMedia media;
    @FXML
    private ComboBox<String> cbBrandName;
    @FXML
    private TextField tfCatagoryName;
    @FXML
    private TextArea taCatagoryDescription;
    @FXML
    private ComboBox<String> cbSupplyerName;
    @FXML
    private Button btnAddSupplyer;
    @FXML
    private Button btnAddBrand;
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
    private void btnSaveCatagory(ActionEvent event) {
        if (isNotNull()) {
            this.catagory.brandName = ((String) this.cbBrandName.getSelectionModel().getSelectedItem());
            this.catagory.supplyerName = ((String) this.cbSupplyerName.getSelectionModel().getSelectedItem());
            this.catagory.catagoryName = this.tfCatagoryName.getText().trim();
            this.catagory.catagoryDescription = this.taCatagoryDescription.getText().trim();
            this.catagory.creatorId = this.userId;
            this.catagoryBLL.save(this.catagory);
        }
    }
    @FXML
    private void btnAddSupplyerOnAction(ActionEvent event) {
        AddSupplyerController addSupplyerController = new AddSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddSupplier.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddSupplyerController supplyerController = (AddSupplyerController) fxmlLoader.getController();
            media.setId(this.userId);
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
    @FXML
    private void btnAddBrandOnAction(ActionEvent event) {
        AddBrandController addSupplyerController = new AddBrandController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBrand.fxml"));
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
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        System.out.println("Clicked");
        if (isNotNull()) {
            this.catagory.id = this.catagoryId;
            if (!this.cbBrandName.getSelectionModel().isEmpty()) {
                this.catagory.brandName = ((String) this.cbBrandName.getSelectionModel().getSelectedItem());
            } else if (!this.cbBrandName.getPromptText().isEmpty()) {
                this.catagory.brandName = this.cbBrandName.getPromptText();
            }
            if (!this.cbSupplyerName.getSelectionModel().isEmpty()) {
                this.catagory.supplyerName = ((String) this.cbSupplyerName.getSelectionModel().getSelectedItem());
            } else if (!this.cbSupplyerName.getPromptText().isEmpty()) {
                this.catagory.supplyerName = this.cbSupplyerName.getPromptText();
            }
            this.catagory.catagoryName = this.tfCatagoryName.getText().trim();
            this.catagory.catagoryDescription = this.taCatagoryDescription.getText().trim();
            this.catagoryBLL.update(this.catagory);
        }
    }
    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    public boolean isNotNull() {
        boolean isNotNull;
        if ((this.tfCatagoryName.getText().trim().isEmpty()) ||
                ((this.cbSupplyerName.getSelectionModel().isEmpty()) &&
                        (this.cbSupplyerName.getPromptText().isEmpty())) || (
                (this.cbBrandName.getSelectionModel().isEmpty()) &&
                        (this.cbBrandName.getPromptText().isEmpty()))) {
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
    @FXML
    private void cbSupplyerNameOnClick(MouseEvent event) {
        this.cbBrandName.getItems().clear();
        this.cbBrandName.setPromptText(null);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.supplyerName = this.rs.getString(2);
                this.cbSupplyerName.getItems().remove(this.supplyerName);
                this.cbSupplyerName.getItems().add(this.supplyerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cbBrandNameOnClick(MouseEvent event) throws SQLException {
        this.cbBrandName.getItems().clear();
        this.supplyerName = ((String) this.cbSupplyerName.getSelectionModel().getSelectedItem());
        this.supplyerId = this.sql.getIdNo(this.supplyerName, this.supplyerId, "Supplyer", "SupplyerName");
        this.pst = this.con.prepareStatement("select * from " + this.db + ".Brands where SupplyerId=?");
        this.pst.setString(1, this.supplyerId);
        this.rs = this.pst.executeQuery();
        while (this.rs.next()) {
            this.cbBrandName.getItems().add(this.rs.getString(2));
        }
    }
    public void showDetails() {
        this.catagory.id = this.catagoryId;
        this.catagoryGetway.selectedView(this.catagory);
        this.tfCatagoryName.setText(this.catagory.catagoryName);
        this.taCatagoryDescription.setText(this.catagory.catagoryDescription);
        this.cbBrandName.setPromptText(this.catagory.brandName);
        this.cbSupplyerName.setPromptText(this.catagory.supplyerName);
    }
}