package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.CurrentProductBLL;
import com.exuberant.ims.controller.application.sell.NewSellController;
import com.exuberant.ims.controller.application.sell.ViewCustomerController;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.dal.ProductGui;
import com.exuberant.ims.gateway.CurrentProductGateway;
import com.exuberant.ims.list.ListProduct;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
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
import java.util.List;
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
    CurrentProductGateway currentProductGateway = new CurrentProductGateway();
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
    private Long usrId;
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
    private TableView<List<CurrentProduct>> tblViewCurrentStore;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductUnit;
    @FXML
    private TableColumn<Object, Object> tblClmProductRMA;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplier;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCategory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductDate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductDescription;
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
        this.productCurrent.setProductId(this.tfSearch.getText());
        this.productCurrent.setProductName(this.tfSearch.getText());
        this.currentProductGateway.searchView(this.productCurrent);
    }

    @FXML
    private void cbSoteViewSupplyerOnClick(MouseEvent event) {
    }

    @FXML
    private void cbSoteViewBrandOnClick(MouseEvent event) {
    }

    @FXML
    private void cbSoteViewCatagoryOnClick(MouseEvent event) {
    }

    @FXML
    private void cbSoteViewRMAOnClick(MouseEvent event) {
    }

    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddProductController apc = new AddProductController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddProduct.fxml");
        fxmlLoader.setLocation(resource);
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
            this.productCurrent.setId(item);
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
        //this.tblViewCurrentStore.setItems(this.productCurrent.getcurrentProductList);
        this.tblClmProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        this.tblClmProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        this.tblClmProductQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.tblClmProductDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tblClmProductSupplier.setCellValueFactory(new PropertyValueFactory("suppliedBy"));
        this.tblClmProductBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        this.tblClmProductCategory.setCellValueFactory(new PropertyValueFactory("catagory"));
        this.tblClmProductUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        this.tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        this.tblClmProductRMA.setCellValueFactory(new PropertyValueFactory("rma"));
        this.tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory("user"));
        this.tblClmProductDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.currentProductGateway.viewFistTen(this.productCurrent);
    }

    private void viewSelected() {
        AddProductController apc = new AddProductController();
        UserNameMedia userMedia = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/stock/AddProduct.fxml");
        fxmlLoader.setLocation(resource);
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
            URL resource = URLService.getFileAsResoure("application/stock/NewSell.fxml");
            fXMLLoader.setLocation(resource);
            try {
                fXMLLoader.load();
                Parent parent = (Parent) fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                NewSellController newSellController = (NewSellController) fXMLLoader.getController();
                newSellController.tfProductId.setText(item);
                newSellController.tfProductIdOnAction(event);
                media.setId(this.usrId);
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
            this.productCurrent.setSupplierName(this.suplyerName);
            this.currentProductGateway.searchBySupplyer(this.productCurrent);
        }
    }

    @FXML
    private void cbSoteViewBrandOnAction(ActionEvent event) {
        if (this.cbSoteViewBrands.getSelectionModel().getSelectedItem() != null) {
            this.brandName = ((String) this.cbSoteViewBrands.getSelectionModel().getSelectedItem());
            this.suplyerName = this.cbSoteViewSupplyer.getPromptText();
            this.productCurrent.setSupplierName(this.suplyerName);
            this.productCurrent.setBrandName(this.brandName);
            this.currentProductGateway.searchByBrand(this.productCurrent);
        }
    }

    @FXML
    private void cbSoteViewCatagoryOnAction(ActionEvent event) {
        if (this.cbSoteViewCatagory.getSelectionModel().getSelectedItem() != null) {
            this.brandName = ((String) this.cbSoteViewBrands.getSelectionModel().getSelectedItem());
            this.suplyerName = this.cbSoteViewSupplyer.getPromptText();
            this.catagoryName = ((String) this.cbSoteViewCatagory.getSelectionModel().getSelectedItem());
            this.productCurrent.setSupplierName(this.suplyerName);
            this.productCurrent.setBrandName(this.brandName);
            this.productCurrent.setCatagoryName(this.catagoryName);
            this.currentProductGateway.searchByCatagory(this.productCurrent);
        }
    }

    public void settingPermission() {
    }

    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        this.tfSearch.clear();
        this.cbSoteViewRMA.getItems().clear();
        this.cbSoteViewSupplyer.getItems().clear();
        this.cbSoteViewBrands.getItems().clear();
        this.cbSoteViewCatagory.getItems().clear();
        this.cbSoteViewSupplyer.setPromptText("Select supplier");
        this.cbSoteViewBrands.setPromptText("select brand");
        this.cbSoteViewCatagory.setPromptText("select category");
        this.cbSoteViewRMA.setPromptText("select rma");
        /*List<CurrentProduct> currentProducts = HibernateRepository.getRepository().getAll(CurrentProduct.class);
        this.tblViewCurrentStore.setItems(observableList(currentProducts));*/
        this.tblClmProductId.setCellValueFactory(new PropertyValueFactory("productId"));
        this.tblClmProductName.setCellValueFactory(new PropertyValueFactory("productName"));
        this.tblClmProductQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.tblClmProductDescription.setCellValueFactory(new PropertyValueFactory("description"));
        this.tblClmProductSupplier.setCellValueFactory(new PropertyValueFactory("suppliedBy"));
        this.tblClmProductBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        this.tblClmProductCategory.setCellValueFactory(new PropertyValueFactory("catagory"));
        this.tblClmProductUnit.setCellValueFactory(new PropertyValueFactory("unit"));
        this.tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory("pursesPrice"));
        this.tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        this.tblClmProductRMA.setCellValueFactory(new PropertyValueFactory("rma"));
        this.tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory("user"));
        this.tblClmProductDate.setCellValueFactory(new PropertyValueFactory("date"));
        this.currentProductGateway.view(this.productCurrent);
    }

    @FXML
    private void cbSoteViewRMAOnAction(ActionEvent event) {
        this.productCurrent.setRmaId(this.rmaID);
        this.currentProductGateway.searchByRMA(this.productCurrent);
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }

    public void doEditCancel(TableColumn.CellEditEvent<ProductGui, String> objectObjectCellEditEvent) {
        System.err.println("doEditCancel");
    }

    public void doCommit(TableColumn.CellEditEvent<ProductGui, String> objectObjectCellEditEvent) {
        System.err.println("doCommit");
    }

    public void doEdit(TableColumn.CellEditEvent<ProductGui, String> objectObjectCellEditEvent) {
        System.err.println("doEdit");
    }

    public void doEditTable(MouseEvent mouseEvent) {
        System.err.println("doEditTable");
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {

    }
}
