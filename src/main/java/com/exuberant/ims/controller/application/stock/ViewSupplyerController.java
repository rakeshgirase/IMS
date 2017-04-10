package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.SupplyerBLL;
import com.exuberant.ims.custom.History;
import com.exuberant.ims.dal.Supplier;
import com.exuberant.ims.getway.SupplyerGetway;
import com.exuberant.ims.list.ListSupplyer;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
public class ViewSupplyerController implements Initializable {
    private static final int dataSize = 10023;
    private static final int rowsPerPage = 1000;
    @FXML
    public AnchorPane acContent;
    Supplier supplier = new Supplier();
    SupplyerGetway supplyerGetway = new SupplyerGetway();
    SupplyerBLL supplyerBLL = new SupplyerBLL();
    History history = new History();
    private Long userId;
    private String creatorName;
    private String creatorId;
    private String supplyerId;
    private String userName;
    private UserNameMedia media;
    @FXML
    private TableView<ListSupplyer> tblSupplyer;
    @FXML
    private TableColumn<Object, Object> clmSUpplyerId;
    @FXML
    private TableColumn<Object, Object> clmSupplyerName;
    @FXML
    private TableColumn<Object, Object> clmSupplyerPhoneNumber;
    @FXML
    private TableColumn<Object, Object> clmSupplyerAddress;
    @FXML
    private TableColumn<Object, Object> clmSupplyerJoining;
    @FXML
    private TableColumn<Object, Object> clmSupplyerDescription;
    @FXML
    private Button btnAdditems;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfSearch;
    private Text text;
    @FXML
    private MenuItem mbSearch;
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
    }
    @FXML
    private void tblSupplyerOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }
    }
    @FXML
    private void tblSupplyerOnKeyPress(KeyEvent event) {
    }
    @FXML
    public void tfSearchOnType(Event event) {
        this.supplier.supplierDetails.removeAll(new ListSupplyer[0]);
        this.supplier.supplierName = this.tfSearch.getText().trim();
        this.tblSupplyer.setItems(this.supplier.supplierDetails);
        this.clmSUpplyerId.setCellValueFactory(new PropertyValueFactory("supplyerId"));
        this.clmSupplyerName.setCellValueFactory(new PropertyValueFactory("supplierName"));
        this.clmSupplyerPhoneNumber.setCellValueFactory(new PropertyValueFactory("supplyerPhoneNumber"));
        this.clmSupplyerAddress.setCellValueFactory(new PropertyValueFactory("supplierAddress"));
        this.clmSupplyerDescription.setCellValueFactory(new PropertyValueFactory("supplierDescription"));
        this.clmSupplyerJoining.setCellValueFactory(new PropertyValueFactory("dataOfjoining"));
        this.supplyerGetway.searchView(this.supplier);
    }
    public void showDetails() {
        this.tblSupplyer.setItems(this.supplier.supplierDetails);
        this.clmSUpplyerId.setCellValueFactory(new PropertyValueFactory("supplyerId"));
        this.clmSupplyerName.setCellValueFactory(new PropertyValueFactory("supplierName"));
        this.clmSupplyerPhoneNumber.setCellValueFactory(new PropertyValueFactory("supplyerPhoneNumber"));
        this.clmSupplyerAddress.setCellValueFactory(new PropertyValueFactory("supplierAddress"));
        this.clmSupplyerDescription.setCellValueFactory(new PropertyValueFactory("supplierDescription"));
        this.clmSupplyerJoining.setCellValueFactory(new PropertyValueFactory("dataOfjoining"));
        this.supplyerGetway.view(this.supplier);
    }
    @FXML
    private void btnAdditemsOnAction(ActionEvent event) {
        AddSupplyerController addSupplyerController = new AddSupplyerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(URLService.getFileAsResoure("application/stock/AddSupplier.fxml"));
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
        tfSearchOnType(event);
    }
    @FXML
    private void btnUpdateOnAction(Event event) {
        if (this.tblSupplyer.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    private void viewDetails() {
        if (!this.tblSupplyer.getSelectionModel().isEmpty()) {
            ListSupplyer selectedSupplyer = (ListSupplyer) this.tblSupplyer.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedSupplyer.getSupplyerId());
            String items = selectedSupplyer.getSupplyerId();
            if (!items.equals(null)) {
                AddSupplyerController addSupplyerController = new AddSupplyerController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(URLService.getFileAsResoure("application/stock/AddSupplier.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = (Parent) fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    AddSupplyerController supplyerController = (AddSupplyerController) fxmlLoader.getController();
                    media.setId(this.userId);
                    supplyerController.setMedia(media);
                    supplyerController.lblCaption.setText("Supplier Details");
                    supplyerController.btnUpdate.setVisible(true);
                    supplyerController.btnSave.setVisible(false);
                    supplyerController.supplyerId = selectedSupplyer.getSupplyerId();
                    supplyerController.showDetails();
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
    private void mbView(ActionEvent event) {
        btnUpdateOnAction(event);
    }
    @FXML
    private void mbViewHistory(ActionEvent event) {
    }
    @FXML
    private void mbAddNew(ActionEvent event) {
        btnAdditemsOnAction(event);
    }
    @FXML
    private void mbDeleteItem(ActionEvent event) {
        System.out.println("clicked to delete");
        this.acContent.setOpacity(0.5D);
        ListSupplyer selectedSupplyer = (ListSupplyer) this.tblSupplyer.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            this.supplier.id = selectedSupplyer.getSupplyerId();
            System.out.println(this.supplier.id + "On hear");
            this.supplyerBLL.delete(this.supplier);
            this.tblSupplyer.getItems().clear();
            showDetails();
        }
    }
    @FXML
    private void mbEdit(ActionEvent event) {
        btnUpdateOnAction(event);
        tfSearchOnType(event);
    }
    @FXML
    private void mbSearch(ActionEvent event) {
        this.tblSupplyer.getSelectionModel().clearSelection();
        this.tfSearch.requestFocus();
    }
    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        mbDeleteItem(event);
    }
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.supplier.supplierDetails.clear();
        showDetails();
    }
}
