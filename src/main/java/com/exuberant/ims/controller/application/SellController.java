package com.exuberant.ims.controller.application;

import com.exuberant.ims.controller.application.sell.ViewCustomerController;
import com.exuberant.ims.controller.application.sell.ViewSellController;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellController implements Initializable {
    @FXML
    public AnchorPane acMainSells;
    UserNameMedia nameMedia;
    Long userId;
    @FXML
    private ToggleButton tbtnSell;
    @FXML
    private ToggleButton tbtnCustomer;
    @FXML
    private ToggleButton tbtnReports;
    @FXML
    private Label lblPathInfo;
    @FXML
    private StackPane spMainContent;

    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        this.tbtnSell.setSelected(true);
        this.tbtnCustomer.setToggleGroup(group);
        this.tbtnSell.setToggleGroup(group);
        this.tbtnReports.setToggleGroup(group);
    }

    @FXML
    public void tbtnSellOnAction(ActionEvent event) throws IOException {
        this.lblPathInfo.setText("Sells");
        ViewSellController sellController = new ViewSellController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/sell/ViewSell.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewSellController controller = (ViewSellController) fXMLLoader.getController();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) throws IOException {
        this.lblPathInfo.setText("Customers");
        ViewCustomerController customerController = new ViewCustomerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/sell/ViewCustomer.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewCustomerController controller = (ViewCustomerController) fXMLLoader.getController();
        controller.setNameMedia(this.nameMedia);
        controller.viewDetails();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnReportsOnAction(ActionEvent event)
            throws IOException {
    }
}
