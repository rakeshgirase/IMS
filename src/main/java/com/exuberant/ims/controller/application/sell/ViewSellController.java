package com.exuberant.ims.controller.application.sell;

import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.getway.CustomerGetway;
import com.exuberant.ims.list.ListCustomer;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ViewSellController
        implements Initializable {
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    Long userId;
    UserNameMedia nameMedia;
    @FXML
    private AnchorPane acCustomerMainContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListCustomer> tblCustomer;
    @FXML
    private TableColumn<Object, Object> tblClmName;
    @FXML
    private TableColumn<Object, Object> tblClmContNo;
    @FXML
    private TableColumn<Object, Object> tblClmAddres;
    @FXML
    private TableColumn<Object, Object> tblClmDate;
    @FXML
    private TableColumn<Object, Object> tblClmAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmTotalBuy;
    @FXML
    private Button btnRefresh;
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void tfSearchOnKeyReleased(Event event) {
        SortedList<ListCustomer> sortList = new SortedList(this.customer.customerList);
        this.customer.customerName = this.tfSearch.getText().trim();
        this.tblCustomer.setItems(this.customer.customerList);
        this.tblClmName.setCellValueFactory(new PropertyValueFactory("customerName"));
        this.tblClmContNo.setCellValueFactory(new PropertyValueFactory("customerContNo"));
        this.tblClmAddres.setCellValueFactory(new PropertyValueFactory("customerAddress"));
        this.tblClmTotalBuy.setCellValueFactory(new PropertyValueFactory("totalBuy"));
        this.tblClmDate.setCellValueFactory(new PropertyValueFactory("addedDate"));
        this.tblClmAddBy.setCellValueFactory(new PropertyValueFactory("addBy"));
        this.customerGetway.searchView(this.customer);
    }
    @FXML
    private void btnAddOnAction(ActionEvent event) {
        AddCustomerController acc = new AddCustomerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/sell/AddCustomer.fxml");
        fXMLLoader.setLocation(resource);
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
            Logger.getLogger(ViewSellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (this.tblCustomer.getSelectionModel().getSelectedItem() != null) {
            selectedView();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (this.tblCustomer.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("CONFIRM");
            alert.setContentText("Are You Sure to Delete this item??");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                ListCustomer listCustomer = (ListCustomer) this.tblCustomer.getSelectionModel().getSelectedItem();
                String item = listCustomer.getId();
                this.customer.id = item;
                this.customerGetway.delete(this.customer);
                this.tblCustomer.getItems().clear();
                viewDetails();
            }
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    public void viewDetails() {
        this.tblCustomer.setItems(this.customer.customerList);
        this.tblClmName.setCellValueFactory(new PropertyValueFactory("customerName"));
        this.tblClmContNo.setCellValueFactory(new PropertyValueFactory("customerContNo"));
        this.tblClmAddres.setCellValueFactory(new PropertyValueFactory("customerAddress"));
        this.tblClmTotalBuy.setCellValueFactory(new PropertyValueFactory("totalBuy"));
        this.tblClmDate.setCellValueFactory(new PropertyValueFactory("addedDate"));
        this.tblClmAddBy.setCellValueFactory(new PropertyValueFactory("addBy"));
        this.customerGetway.view(this.customer);
    }
    public void selectedView() {
        ListCustomer listCustomer = (ListCustomer) this.tblCustomer.getSelectionModel().getSelectedItem();
        String item = listCustomer.getId();
        if (!item.isEmpty()) {
            AddCustomerController acc = new AddCustomerController();
            UserNameMedia media = new UserNameMedia();
            FXMLLoader fXMLLoader = new FXMLLoader();
            URL resource = URLService.getFileAsResoure("application/sell/AddCustomer.fxml");
            fXMLLoader.setLocation(resource);
            try {
                fXMLLoader.load();
                Parent parent = (Parent) fXMLLoader.getRoot();
                Scene scene = new Scene(parent);
                scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                AddCustomerController addCustomerController = (AddCustomerController) fXMLLoader.getController();
                media.setId(this.userId);
                addCustomerController.setNameMedia(this.nameMedia);
                addCustomerController.lblCustomerContent.setText("Customer Details");
                addCustomerController.btnSave.setVisible(false);
                addCustomerController.customerId = listCustomer.getId();
                addCustomerController.viewDetails();
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ViewSellController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @FXML
    private void tblCustomerOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedView();
        } else {
            System.out.println("CLICK");
        }
    }
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.tfSearch.clear();
        this.customer.customerList.clear();
        viewDetails();
    }
}
