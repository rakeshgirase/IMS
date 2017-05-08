package com.exuberant.ims.controller.application.sell;

import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.gateway.CustomerGateway;
import com.exuberant.ims.list.CustomerGui;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewSellController
        implements Initializable {
    Customer customer = new Customer();
    CustomerGateway customerGateway = new CustomerGateway();
    Long userId;
    private ObservableList<CustomerGui> data;
    @FXML
    private AnchorPane acCustomerMainContent;
    @FXML
    private TextField tfSearch;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void tfSearchOnKeyReleased(Event event) {
        this.customerGateway.searchView(this.customer);
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
    private void btnDeleteOnAction(ActionEvent event) {
    }


    public void selectedView() {
        CustomerGui customerGui = new CustomerGui();
        String item = customerGui.getId();
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
                addCustomerController.lblCustomerContent.setText("Customer Details");
                addCustomerController.btnSave.setVisible(false);
                addCustomerController.customerId = customerGui.getId();
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
        data.clear();
    }

    public void btnSellOrderOnAction(ActionEvent actionEvent) {
    }
}
