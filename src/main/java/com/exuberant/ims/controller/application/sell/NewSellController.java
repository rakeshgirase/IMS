package com.exuberant.ims.controller.application.sell;
import com.exuberant.ims.bll.SellCartBLL;
import com.exuberant.ims.dal.CurrentProduct;
import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.getway.CurrentProductGetway;
import com.exuberant.ims.getway.CustomerGetway;
import com.exuberant.ims.getway.SellCartGerway;
import com.exuberant.ims.list.ListCustomer;
import com.exuberant.ims.list.ListPreSell;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.custom.RandomIdGenarator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class NewSellController
        implements Initializable {
    public Button btnAddCustomer;
    @FXML
    public TextField tfProductId;
    UserNameMedia nameMedia;
    String userId;
    String customerId;
    int quantity;
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    CurrentProduct currrentProduct = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    SellCart sellCart = new SellCart();
    SellCartGerway sellCartGerway = new SellCartGerway();
    SellCartBLL scbll = new SellCartBLL();
    CustomTf ctf = new CustomTf();
    ObservableList<ListPreSell> preList = FXCollections.observableArrayList();
    @FXML
    private MenuButton mbtnCustomer;
    @FXML
    private TableView<ListCustomer> tblCustomerSortView;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerName;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerPhoneNo;
    @FXML
    private TextField tfCustomerSearch;
    @FXML
    private Button btnClose;
    @FXML
    private TableView<ListPreSell> tblSellPreList;
    @FXML
    private TableColumn<Object, Object> tblClmSellId;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmCustomer;
    @FXML
    private TableColumn<Object, Object> tblClmSolledBy;
    @FXML
    private TableColumn<Object, Object> tblClmWarrentyVoidDate;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    @FXML
    private TextField tfQuantity;
    @FXML
    private Label lblCurrentQuantity;
    @FXML
    private TextField tfSellPrice;
    @FXML
    private Label lblPursesPrice;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblNetCost;
    private Label lblDiscount;
    @FXML
    private Label lblUnit;
    @FXML
    private TextField tfSupplyer;
    @FXML
    private TextField tfBrand;
    @FXML
    private TextField tfCatagory;
    @FXML
    private TextField tfWarrentVoidDate;
    @FXML
    private Button btnAddToChart;
    @FXML
    private Button btnSell;
    @FXML
    private Label lblPursesDate;
    @FXML
    private Label lblTotalItems;
    @FXML
    private TextField tfProductName;
    @FXML
    private Button btnClearAll;
    @FXML
    private Button btnClearSelected;
    @FXML
    private Label lblSellId;
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL location, ResourceBundle resources) {
        clearAll();
    }
    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
        this.mbtnCustomer.setText(((ListCustomer) this.tblCustomerSortView.getSelectionModel().getSelectedItem()).getCustomerName());
        this.customerId = ((ListCustomer) this.tblCustomerSortView.getSelectionModel().getSelectedItem()).getId();
        System.out.println(this.customerId);
    }
    @FXML
    private void mbtnCustomerOnClicked(MouseEvent event) {
        this.customer.customerName = this.tfCustomerSearch.getText().trim();
        this.tblCustomerSortView.setItems(this.customer.customerList);
        this.tblClmCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        this.tblClmCustomerPhoneNo.setCellValueFactory(new PropertyValueFactory("customerContNo"));
        this.customerGetway.searchView(this.customer);
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void tfCustomerSearchOnKeyReleased(KeyEvent event) {
        this.customer.customerName = this.tfCustomerSearch.getText().trim();
        this.tblCustomerSortView.setItems(this.customer.customerList);
        this.tblClmCustomerName.setCellValueFactory(new PropertyValueFactory("customerName"));
        this.tblClmCustomerPhoneNo.setCellValueFactory(new PropertyValueFactory("customerContNo"));
        this.customerGetway.searchView(this.customer);
    }
    @FXML
    public void tfProductIdOnAction(ActionEvent event) {
        if (this.tfProductId.getText().isEmpty()) {
            clearAll();
        } else {
            this.currrentProduct.productId = this.tfProductId.getText().trim();
            this.currentProductGetway.sView(this.currrentProduct);
            this.lblUnit.setText(this.currrentProduct.unitName);
            this.lblCurrentQuantity.setText(this.currrentProduct.quantity);
            this.lblPursesPrice.setText(this.currrentProduct.pursesPrice);
            this.tfBrand.setText(this.currrentProduct.brandName);
            this.tfSupplyer.setText(this.currrentProduct.supplierName);
            this.tfCatagory.setText(this.currrentProduct.catagoryName);
            this.tfWarrentVoidDate.setText(this.currrentProduct.warrentyVoidDate);
            this.lblPursesDate.setText(this.currrentProduct.date);
            this.tfProductName.setText(this.currrentProduct.productName);
            this.tfSellPrice.setText(this.currrentProduct.sellPrice);
        }
    }
    @FXML
    private void btnAddToChartOnAction(ActionEvent event) {
        if (inNotNull()) {
            this.preList.add(new ListPreSell(this.currrentProduct.id, this.currrentProduct.productId, this.customerId, this.currrentProduct.pursesPrice, this.tfSellPrice.getText(), this.lblCurrentQuantity.getText(), this.tfQuantity.getText(), this.lblNetCost.getText(), this.currrentProduct.date, this.tfWarrentVoidDate.getText(), this.userId, LocalDateTime.now().toString()));
            viewAll();
            sumTotalCost();
            clearAll();
        }
    }
    private void sumTotalCost() {
        this.tblSellPreList.getSelectionModel().selectFirst();
        float sum = 0.0F;
        int items = this.tblSellPreList.getItems().size();
        for (int i = 0; i < items; i++) {
            this.tblSellPreList.getSelectionModel().select(i);
            String selectedItem = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getTotalPrice();
            float newFloat = Float.parseFloat(selectedItem);
            sum += newFloat;
        }
        String totalCost = String.valueOf(sum);
        this.lblTotal.setText(totalCost);
        System.out.println("Total:" + sum);
        String totalItem = String.valueOf(items);
        this.lblTotalItems.setText(totalItem);
    }
    public void viewAll() {
        this.tblSellPreList.setItems(this.preList);
        this.tblClmProductId.setCellValueFactory(new PropertyValueFactory("productID"));
        this.tblClmQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        this.tblClmSellPrice.setCellValueFactory(new PropertyValueFactory("sellPrice"));
        this.tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory("totalPrice"));
        this.tblClmWarrentyVoidDate.setCellValueFactory(new PropertyValueFactory("warrentyVoidDate"));
    }
    @FXML
    private void btnSellOnAction(ActionEvent event) {
        if (!this.tblSellPreList.getItems().isEmpty()) {
            System.out.println(this.lblSellId.getText());
            int indexs = this.tblSellPreList.getItems().size();
            for (int i = 0; i < indexs; i++) {
                this.tblSellPreList.getSelectionModel().select(i);
                this.sellCart.Id = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getId();
                this.sellCart.productID = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getId();
                this.sellCart.sellID = this.lblSellId.getText();
                this.sellCart.customerID = this.customerId;
                this.sellCart.pursesPrice = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getPursesPrice();
                this.sellCart.sellPrice = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getSellPrice();
                this.sellCart.quantity = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getQuantity();
                this.sellCart.oldQuentity = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getOldQuantity();
                this.sellCart.totalPrice = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getTotalPrice();
                this.sellCart.warrentyVoidDate = ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getWarrentyVoidDate();
                this.sellCart.sellerID = this.userId;
                this.scbll.sell(this.sellCart);
                System.out.println("Old Quentity:" + ((ListPreSell) this.tblSellPreList.getSelectionModel().getSelectedItem()).getOldQuantity());
            }
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Soled");
            alert.setContentText("Soled Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            this.tblSellPreList.getItems().clear();
            this.lblTotal.setText(null);
            System.out.println("Customer ID: " + this.customerId);
        } else {
            System.out.println("EMPTY");
        }
    }
    public void clearAll() {
        this.tfBrand.clear();
        this.tfProductId.clear();
        this.tfCatagory.clear();
        this.tfSellPrice.clear();
        this.tfSupplyer.clear();
        this.tfWarrentVoidDate.clear();
        this.tfQuantity.clear();
        this.tfProductName.clear();
        this.lblCurrentQuantity.setText(null);
        this.lblNetCost.setText(null);
        this.lblPursesPrice.setText(null);
        this.lblUnit.setText(null);
        this.lblPursesDate.setText(null);
    }
    @FXML
    private void tfQuantityOnAction(KeyEvent event) {
        if (!this.tfQuantity.getText().isEmpty()) {
            String givenQuentity = this.tfQuantity.getText();
            int givenQinInt = Integer.parseInt(givenQuentity);
            String currentQuentity = this.lblCurrentQuantity.getText();
            int currentQuentiInt = Integer.parseInt(currentQuentity);
            if (givenQinInt > currentQuentiInt) {
                System.out.println("BIG");
                this.tfQuantity.clear();
                this.lblNetCost.setText("0.0");
            } else {
                this.quantity = Integer.parseInt(this.tfQuantity.getText());
                float sellPrice = Float.parseFloat(this.tfSellPrice.getText());
                String netPrice = String.valueOf(sellPrice * this.quantity);
                this.lblNetCost.setText(netPrice);
            }
        } else {
            this.lblNetCost.setText("0.0");
        }
    }
    @FXML
    private void tfSellPriceOnAction(KeyEvent event) {
        System.out.println("PRESSES");
        if (!this.tfSellPrice.getText().isEmpty()) {
            String quentity = this.tfQuantity.getText();
            int intQuentity = Integer.parseInt(quentity);
            String sellPrice = this.tfSellPrice.getText();
            float fSellPrice = Float.parseFloat(sellPrice);
            String sTotalPrice = String.valueOf(intQuentity * fSellPrice);
            this.lblNetCost.setText(sTotalPrice);
            System.out.println(sTotalPrice);
        } else {
            this.lblNetCost.setText("0.0");
        }
    }
    @FXML
    public void btnAddCustomerOnAction(ActionEvent actionEvent) {
        System.out.println(this.userId);
        AddCustomerController acc = new AddCustomerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/sell/AddCustomer.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = (Parent) fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddCustomerController addCustomerController = (AddCustomerController) fXMLLoader.getController();
            media.setId(this.userId);
            addCustomerController.setNameMedia(this.nameMedia);
            addCustomerController.lblCustomerContent.setText("ADD CUSTOMER");
            addCustomerController.btnUpdate.setVisible(false);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnClearAllOnAction(ActionEvent event) {
    }
    @FXML
    private void btnClearSelectedOnAction(ActionEvent event) {
        if (this.tblSellPreList.getItems().size() != 0) {
            System.out.println("Clicked");
            this.tblSellPreList.getItems().removeAll(this.tblSellPreList.getSelectionModel().getSelectedItems());
            sumTotalCost();
        } else {
            System.out.println("EMPTY");
        }
    }
    public void genarateSellID() {
        String id = RandomIdGenarator.randomstring();
        if (id.matches("001215")) {
            String nId = RandomIdGenarator.randomstring();
            this.lblSellId.setText(nId);
        } else {
            this.lblSellId.setText(id);
        }
    }
    public boolean inNotNull() {
        boolean isNotNull = false;
        if ((this.mbtnCustomer.getText().matches("Select Customer")) || (this.tfSellPrice.getText() == null) || (this.tfQuantity.getText().trim().matches(""))) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please fill all requre field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        }
        isNotNull = true;
        return isNotNull;
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\sell\NewSellController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */