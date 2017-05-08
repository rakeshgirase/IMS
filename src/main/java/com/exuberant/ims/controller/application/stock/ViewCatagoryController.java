package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.CatagoryBLL;
import com.exuberant.ims.custom.CellFactories;
import com.exuberant.ims.dal.Catagory;
import com.exuberant.ims.gateway.CatagoryGateway;
import com.exuberant.ims.list.ListCatagory;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewCatagoryController
        implements Initializable {
    @FXML
    public TableColumn<Object, Object> clmSupplyer;
    @FXML
    public TableColumn tablClmBox;
    Catagory catagory = new Catagory();
    CellFactories cellFactories = new CellFactories();
    CatagoryGateway catagoryGateway = new CatagoryGateway();
    CatagoryBLL catagoryBLL = new CatagoryBLL();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private Long usrId;
    private String catagoryId;
    private String brandId;
    private String brandName;
    private String creatorId;
    private UserNameMedia media;
    @FXML
    private TableView<ListCatagory> tblCatagory;
    @FXML
    private TableColumn<Object, Object> clmCatagoryId;
    @FXML
    private TableColumn<Object, Object> clmCatagoryName;
    @FXML
    private TableColumn<Object, Object> clmCatagoryBrand;
    @FXML
    private TableColumn<Object, Object> clmCatagoryCreator;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDate;
    @FXML
    private TableColumn<Object, Object> clmCatagoryDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField tfSearch;

    public UserNameMedia getMedia() {
        return this.media;
    }

    public void setMedia(UserNameMedia media) {
        this.usrId = media.getId();
        this.media = media;
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void tblCatagoryOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewDetails();
        } else {
            System.out.println("CLICKED");
        }
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
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

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (this.tblCatagory.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        ListCatagory selectedCatagory = (ListCatagory) this.tblCatagory.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            this.catagory.id = selectedCatagory.getId();
            System.out.println(this.catagory.id + "On hear");
            this.catagoryBLL.delete(this.catagory);
            this.tblCatagory.getItems().clear();
            showDetails();
        }
    }

    public void showDetails() {
        this.tblCatagory.setItems(this.catagory.catagoryDetails);
        this.tablClmBox.setCellValueFactory(new PropertyValueFactory("id"));
        this.clmCatagoryId.setCellValueFactory(new PropertyValueFactory("id"));
        this.clmCatagoryName.setCellValueFactory(new PropertyValueFactory("catagoryName"));
        this.clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory("brandId"));
        this.clmSupplyer.setCellValueFactory(new PropertyValueFactory("supplyerId"));
        this.clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory("catagoryDescription"));
        this.clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory("creatorId"));
        this.clmCatagoryDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.catagoryGateway.view(this.catagory);
        this.tablClmBox.setCellFactory(this.cellFactories.cellFactoryCheckBox);
    }

    @FXML
    public void tfSearchOnType(Event event) {
        this.catagory.catagoryDetails.clear();
        this.catagory.catagoryName = this.tfSearch.getText().trim();
        this.tblCatagory.setItems(this.catagory.catagoryDetails);
        this.clmCatagoryId.setCellValueFactory(new PropertyValueFactory("id"));
        this.clmCatagoryName.setCellValueFactory(new PropertyValueFactory("catagoryName"));
        this.clmCatagoryBrand.setCellValueFactory(new PropertyValueFactory("brandId"));
        this.clmSupplyer.setCellValueFactory(new PropertyValueFactory("supplyerId"));
        this.clmCatagoryDescription.setCellValueFactory(new PropertyValueFactory("catagoryDescription"));
        this.clmCatagoryCreator.setCellValueFactory(new PropertyValueFactory("creatorId"));
        this.clmCatagoryDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.catagoryGateway.searchView(this.catagory);
    }

    private void viewDetails() {
        if (!this.tblCatagory.getSelectionModel().isEmpty()) {
            ListCatagory selectedCatagory = (ListCatagory) this.tblCatagory.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedCatagory.getCreatorId());
            String items = selectedCatagory.getId();
            if (!items.equals(null)) {
                AddCatagoryController addCatagoryController = new AddCatagoryController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(URLService.getFileAsResoure("application/stock/AddCategory.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = (Parent) fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    AddCatagoryController catagoryController = (AddCatagoryController) fxmlLoader.getController();
                    media.setId(this.usrId);
                    catagoryController.setMedia(media);
                    catagoryController.lblHeaderContent.setText("Catagory Details");
                    catagoryController.btnUpdate.setVisible(true);
                    catagoryController.btnSave.setVisible(false);
                    catagoryController.catagoryId = selectedCatagory.id;
                    catagoryController.showDetails();
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
    private void miSearchOnAction(ActionEvent event) {
        this.tblCatagory.getSelectionModel().clearSelection();
        this.tfSearch.requestFocus();
    }

    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }

    @FXML
    private void miViewOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.catagory.catagoryDetails.clear();
        showDetails();
    }
}
