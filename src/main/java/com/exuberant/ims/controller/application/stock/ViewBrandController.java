package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.BrandBLL;
import com.exuberant.ims.dal.Brands;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.SQL;
import com.exuberant.ims.getway.BrandsGetway;
import com.exuberant.ims.list.ListBrands;
import com.exuberant.ims.media.UserNameMedia;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
public class ViewBrandController
        implements Initializable {
    SQL sql = new SQL();
    Brands brands = new Brands();
    BrandsGetway brandsGetway = new BrandsGetway();
    BrandBLL brandBLL = new BrandBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Callback callback = new Callback<TableColumn<Object, Object>, TableCell<Object, Object>>() {

        @Override
        public TableCell<Object, Object> call(TableColumn<Object, Object> param) {
            TableCell cell = new TableCell() {
                public void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);
                    Text text = new Text();
                    if (!isEmpty()) {
                        text = new Text(item.toString());
                        text.setWrappingWidth(200.0D);
                        text.setFill(Color.BLACK);
                        setGraphic(text);
                    } else if (isEmpty()) {
                        text.setText(null);
                        setGraphic(null);
                    }
                }
            };
            return cell;
        }
    };
    private String usrId;
    private String usrName;
    private String brandId;
    private String creatorId;
    private String supplyerId;
    private UserNameMedia media;
    @FXML
    private TableView<ListBrands> tblBrand;
    @FXML
    private TableColumn<Object, Object> tblCollumId;
    @FXML
    private TableColumn<Object, Object> tblCollumName;
    @FXML
    private TableColumn<Object, Object> tblCollumSupplyer;
    @FXML
    private TableColumn<Object, Object> tblCollumDescription;
    @FXML
    private TableColumn<Object, Object> tblCollumCreator;
    @FXML
    private TableColumn<Object, Object> tblClmDate;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAddBrand;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnRefresh;
    public UserNameMedia getMedia() {
        return this.media;
    }
    public void setMedia(UserNameMedia media) {
        this.usrId = media.getId();
        this.usrName = media.getUserName();
        this.media = media;
    }
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    public void tblBrandOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }
    }
    @FXML
    private void tfSearchOnKeyPress(Event event) {
        System.out.println(this.usrId);
        this.brands.brandDitails.clear();
        this.brands.brandName = this.tfSearch.getText();
        this.tblBrand.setItems(this.brands.brandDitails);
        this.tblCollumId.setCellValueFactory(new PropertyValueFactory("id"));
        this.tblCollumName.setCellValueFactory(new PropertyValueFactory("brandName"));
        this.tblCollumSupplyer.setCellValueFactory(new PropertyValueFactory("supplyerName"));
        this.tblCollumDescription.setCellValueFactory(new PropertyValueFactory("brandComment"));
        this.tblCollumCreator.setCellValueFactory(new PropertyValueFactory("creatorId"));
        this.tblClmDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.brandsGetway.searchView(this.brands);
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
        tfSearchOnAction(event);
    }
    @FXML
    private void btnUpdateOnAction(Event event) {
        if (this.tblBrand.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    @FXML
    private void btnDeleteOnAction(Event event) {
        ListBrands selectedBrand = (ListBrands) this.tblBrand.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            this.brands.id = selectedBrand.getId();
            System.out.println(this.brands.id + "On hear");
            this.brandBLL.delete(this.brands);
            this.tblBrand.getItems().clear();
            showDetails();
        }
    }
    public void showDetails() {
        this.tblBrand.setItems(this.brands.brandDitails);
        this.tblCollumId.setCellValueFactory(new PropertyValueFactory("id"));
        this.tblCollumName.setCellValueFactory(new PropertyValueFactory("brandName"));
        this.tblCollumSupplyer.setCellValueFactory(new PropertyValueFactory("supplyerName"));
        this.tblCollumDescription.setCellValueFactory(new PropertyValueFactory("brandComment"));
        this.tblCollumCreator.setCellValueFactory(new PropertyValueFactory("creatorId"));
        this.tblClmDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.brandsGetway.view(this.brands);
    }
    @FXML
    public void tfSearchOnAction(ActionEvent event) {
    }
    private void viewDetails() {
        ListBrands selectedBrand = (ListBrands) this.tblBrand.getSelectionModel().getSelectedItem();
        String items = selectedBrand.getId();
        if (!items.equals(null)) {
            AddBrandController addBrandController1 = new AddBrandController();
            UserNameMedia media = new UserNameMedia();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBrand.fxml"));
            try {
                fxmlLoader.load();
                Parent parent = (Parent) fxmlLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                AddBrandController addBrandController = (AddBrandController) fxmlLoader.getController();
                media.setId(this.usrId);
                addBrandController.setMedia(media);
                addBrandController.lblHeader.setText("Brand Details");
                addBrandController.btnUpdate.setVisible(true);
                addBrandController.btnAddBrand.setVisible(false);
                addBrandController.brandId = selectedBrand.getId();
                addBrandController.showDetails();
                Stage nStage = new Stage();
                nStage.setScene(scene);
                nStage.initModality(Modality.APPLICATION_MODAL);
                nStage.initStyle(StageStyle.TRANSPARENT);
                nStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void miSearch(ActionEvent event) {
        this.tblBrand.getSelectionModel().clearSelection();
        this.tfSearch.requestFocus();
    }
    @FXML
    private void miUpdate(Event event) {
        btnUpdateOnAction(event);
    }
    @FXML
    private void miSeeUpdateHistory(ActionEvent event) {
    }
    @FXML
    private void miDelete(ActionEvent event) {
        btnDeleteOnAction(event);
    }
    @FXML
    private void miAddNew(ActionEvent event) {
        btnAddBrandOnAction(event);
    }
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.brands.brandDitails.clear();
        showDetails();
    }
}
