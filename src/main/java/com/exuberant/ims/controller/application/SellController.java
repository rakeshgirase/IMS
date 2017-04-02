package com.exuberant.ims.controller.application;
import com.exuberant.ims.controller.application.sell.ViewCustomerController;
import com.exuberant.ims.controller.application.sell.ViewSellController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class SellController
        implements Initializable {
    @FXML
    public AnchorPane acMainSells;
    UserNameMedia nameMedia;
    String userId;
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
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
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
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewSell.fxml").openStream());
        media.setId(this.userId);
        ViewSellController controller = (ViewSellController) fXMLLoader.getController();
        controller.setNameMedia(this.nameMedia);
        controller.viewDetails();
        this.spMainContent.getChildren().clear();
        this.spMainContent.getChildren().add(fXMLLoader.getRoot());
    }
    @FXML
    private void tbtnCustomerOnAction(ActionEvent event) throws IOException {
        this.lblPathInfo.setText("Customers");
        ViewCustomerController customerController = new ViewCustomerController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewCustomer.fxml").openStream());
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
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\SellController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */