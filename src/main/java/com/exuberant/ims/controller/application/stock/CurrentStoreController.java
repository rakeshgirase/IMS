package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.CurrentProductBLL;
import com.exuberant.ims.controller.application.SettingsController;
import com.exuberant.ims.controller.application.sell.NewSellController;
import com.exuberant.ims.controller.application.sell.ViewCustomerController;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.SQL;
import com.exuberant.ims.getway.CurrentProductGetway;
import com.exuberant.ims.list.ListProduct;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.util.PropertyService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CurrentStoreController implements Initializable {
    @FXML
    public javafx.scene.layout.StackPane spProductContent;
    @FXML
    public javafx.scene.layout.AnchorPane apCombobox;
    CurrentProduct productCurrent = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    CurrentProductBLL currentProductBLL = new CurrentProductBLL();
    String db = PropertyService.getInstance().getProperty("db");
    String suplyerId;
    String suplyerName;
    String brandId;
    String brandName;
    String catagoryId;
    String catagoryName;
    String rmaID;
    String rmaName;
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private UserNameMedia media;
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<String> cbSoteViewSupplyer;
    @FXML
    private ComboBox<String> cbSoteViewBrands;
    @FXML
    private ComboBox<String> cbSoteViewCatagory;
    @FXML
    private ComboBox<String> cbSoteViewRMA;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListProduct> tblViewCurrentStore;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductUnit;
    @FXML
    private TableColumn<Object, Object> tblClmProductRMA;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplyer;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCatagory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductdate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductdescription;
    @FXML
    private MenuItem miSellSelected;
    @FXML
    private Button btnRefresh;
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
    private void tfSearchOnKeyRelese(KeyEvent event) {
        this.productCurrent.productId = this.tfSearch.getText();
        this.productCurrent.productName = this.tfSearch.getText();
        this.currentProductGetway.searchView(this.productCurrent);
    }
    @FXML
    private void cbSoteViewSupplyerOnClick(MouseEvent event) {
        this.con = this.dbCon.getConnection();
        this.cbSoteViewSupplyer.getItems().clear();
        this.cbSoteViewBrands.setPromptText("Select Brand");
        this.cbSoteViewCatagory.setPromptText("Select Category");
        try {
            this.pst = this.con.prepareStatement("select * from Supplyer");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbSoteViewSupplyer.getItems().remove(this.rs.getString(2));
                this.cbSoteViewSupplyer.getItems().add(this.rs.getString(2));
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void cbSoteViewBrandOnClick(MouseEvent event) {
        this.con = this.dbCon.getConnection();
        this.cbSoteViewBrands.getItems().clear();
        this.suplyerName = ((String) this.cbSoteViewSupplyer.getSelectionModel().getSelectedItem());
        this.suplyerId = this.sql.getIdNo(this.suplyerName, this.suplyerId, "Supplyer", "SupplyerName");
        try {
            this.pst = this.con.prepareStatement("select * from Brands where SupplyerId=?");
            this.pst.setString(1, this.suplyerId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbSoteViewBrands.getItems().add(this.rs.getString(2));
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cbSoteViewCatagoryOnClick(MouseEvent event) {
        this.con = this.dbCon.getConnection();
        this.cbSoteViewCatagory.getItems().clear();
        this.suplyerName = ((String) this.cbSoteViewSupplyer.getSelectionModel().getSelectedItem());
        this.suplyerId = this.sql.getIdNo(this.suplyerName, this.suplyerId, "Supplyer", "SupplyerName");
        this.brandId = this.sql.getBrandID(this.suplyerId, this.brandId, this.brandName);
        try {
            this.pst = this.con.prepareStatement("select * from Catagory where SupplyerId=? and BrandId=?");
            this.pst.setString(1, this.suplyerId);
            this.pst.setString(2, this.brandId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbSoteViewCatagory.getItems().add(this.rs.getString(2));
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void cbSoteViewRMAOnClick(MouseEvent event) {
        this.cbSoteViewRMA.getItems().clear();
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from RMA");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.cbSoteViewRMA.getItems().add(this.rs.getString(2));
            }
            this.rs.close();
            this.con.close();
            this.pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddProductController apc = new AddProductController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddProduct.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddProductController addProductController = (AddProductController) fxmlLoader.getController();
            media.setId(this.usrId);
            addProductController.setNameMedia(media);
            addProductController.lblHeader.setText("Add PRODUCT");
            addProductController.btnUpdate.setVisible(false);
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
        if (!this.tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            String item = ((ListProduct) this.tblViewCurrentStore.getSelectionModel().getSelectedItem()).getId();
            System.out.println("Product id" + item);
            this.productCurrent.id = item;
            this.currentProductBLL.delete(this.productCurrent);
            btnRefreshOnACtion(event);
        }
    }
    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (!this.tblViewCurrentStore.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            this.tblViewCurrentStore.setOnMouseClicked(new javafx.event.EventHandler() {
                @Override
                public void handle(Event event) {
                    CurrentStoreController.this.tblViewCurrentStore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });
        }
    }
    public void viewDetails() {
        System.out.println("CLCKED");
        this.tblViewCurrentStore.setItems(this.productCurrent.currentProductList);
        this.tblClmProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        this.tblClmProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        this.tblClmProductquantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.tblClmProductdescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory("suppliedBy"));
        this.tblClmProductBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        this.tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory("catagory"));
        this.tblClmProductUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        this.tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        this.tblClmProductRMA.setCellValueFactory(new PropertyValueFactory("rma"));
        this.tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory("user"));
        this.tblClmProductdate.setCellValueFactory(new PropertyValueFactory("date"));
        this.currentProductGetway.viewFistTen(this.productCurrent);
    }
    private void viewSelected() {
        AddProductController apc = new AddProductController();
        UserNameMedia userMedia = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddProduct.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddProductController addProductController = (AddProductController) fxmlLoader.getController();
            userMedia.setId(this.usrId);
            addProductController.setNameMedia(userMedia);
            addProductController.lblHeader.setText("PRODUCT DETAILS");
            addProductController.btnUpdate.setVisible(true);
            addProductController.btnSave.setVisible(false);
            addProductController.id = ((ListProduct) this.tblViewCurrentStore.getSelectionModel().getSelectedItem()).getId();
            addProductController.viewSelected();
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
    private void miSellSelectedOnAction(ActionEvent event) {
        if (this.tblViewCurrentStore.getSelectionModel().getSelectedItem() != null) {
            String item = ((ListProduct) this.tblViewCurrentStore.getSelectionModel().getSelectedItem()).getProductId();
            NewSellController acc = new NewSellController();
            UserNameMedia media = new UserNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/view/application/sell/NewSell.fxml"));
            try {
                fXMLLoader.load();
                Parent parent = (Parent) fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                NewSellController newSellController = (NewSellController) fXMLLoader.getController();
                newSellController.tfProductId.setText(item);
                newSellController.tfProductIdOnAction(event);
                media.setId(this.usrId);
                newSellController.setNameMedia(media);
                newSellController.genarateSellID();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    private void cbSoteViewSupplyerOnAction(ActionEvent event) {
        if (this.cbSoteViewSupplyer.getSelectionModel().getSelectedItem() != null) {
            this.suplyerName = ((String) this.cbSoteViewSupplyer.getSelectionModel().getSelectedItem());
            this.productCurrent.supplierName = this.suplyerName;
            this.currentProductGetway.searchBySupplyer(this.productCurrent);
        }
    }
    @FXML
    private void cbSoteViewBrandOnAction(ActionEvent event) {
        if (this.cbSoteViewBrands.getSelectionModel().getSelectedItem() != null) {
            this.brandName = ((String) this.cbSoteViewBrands.getSelectionModel().getSelectedItem());
            this.suplyerName = this.cbSoteViewSupplyer.getPromptText();
            this.productCurrent.supplierName = this.suplyerName;
            this.productCurrent.brandName = this.brandName;
            this.currentProductGetway.searchByBrand(this.productCurrent);
        }
    }
    @FXML
    private void cbSoteViewCatagoryOnAction(ActionEvent event) {
        if (this.cbSoteViewCatagory.getSelectionModel().getSelectedItem() != null) {
            this.brandName = ((String) this.cbSoteViewBrands.getSelectionModel().getSelectedItem());
            this.suplyerName = this.cbSoteViewSupplyer.getPromptText();
            this.catagoryName = ((String) this.cbSoteViewCatagory.getSelectionModel().getSelectedItem());
            this.productCurrent.supplierName = this.suplyerName;
            this.productCurrent.brandName = this.brandName;
            this.productCurrent.catagoryName = this.catagoryName;
            this.currentProductGetway.searchByCatagory(this.productCurrent);
        }
    }
    public void settingPermission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where id=?");
            this.pst.setString(1, this.usrId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt(8) == 0) {
                    this.btnUpdate.setDisable(true);
                    this.btnDelete.setDisable(true);
                }
                if (this.rs.getInt(3) == 0) {
                    this.btnAddNew.setDisable(true);
                }
                if (this.rs.getInt("SellProduct") == 0) {
                    this.miSellSelected.setDisable(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        this.productCurrent.currentProductList.clear();
        this.tfSearch.clear();
        this.cbSoteViewRMA.getItems().clear();
        this.cbSoteViewSupplyer.getItems().clear();
        this.cbSoteViewBrands.getItems().clear();
        this.cbSoteViewCatagory.getItems().clear();
        this.cbSoteViewSupplyer.setPromptText("Select supplier");
        this.cbSoteViewBrands.setPromptText("select brands");
        this.cbSoteViewCatagory.setPromptText("select category");
        this.cbSoteViewRMA.setPromptText("select rma");
        this.tblViewCurrentStore.setItems(this.productCurrent.currentProductList);
        this.tblClmProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        this.tblClmProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        this.tblClmProductquantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.tblClmProductdescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory("suppliedBy"));
        this.tblClmProductBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        this.tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory("catagory"));
        this.tblClmProductUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        this.tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        this.tblClmProductRMA.setCellValueFactory(new PropertyValueFactory("rma"));
        this.tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory("user"));
        this.tblClmProductdate.setCellValueFactory(new PropertyValueFactory("date"));
        this.currentProductGetway.view(this.productCurrent);
    }
    @FXML
    private void cbSoteViewRMAOnAction(ActionEvent event) {
        this.con = this.dbCon.getConnection();
        this.rmaName = ((String) this.cbSoteViewRMA.getSelectionModel().getSelectedItem());
        System.out.println("Rma Name " + this.rmaName);
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".RMA where RMAName=?");
            this.pst.setString(1, this.rmaName);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                System.out.println("in the loop" + this.rs.getString(1));
                this.rmaID = this.rs.getString(1);
                System.out.println("Print rma id" + this.rmaID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.productCurrent.rmaId = this.rmaID;
        this.currentProductGetway.searchByRMA(this.productCurrent);
    }
    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }
}
