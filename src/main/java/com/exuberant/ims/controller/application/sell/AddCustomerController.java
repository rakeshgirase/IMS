package com.exuberant.ims.controller.application.sell;
import com.exuberant.ims.bll.CustomerBLL;
import com.exuberant.ims.dal.Customer;
import com.exuberant.ims.getway.CustomerGetway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.util.ResourceBundle;
public class AddCustomerController
        implements Initializable {
    @FXML
    public Button btnSave;
    @FXML
    public Label lblCustomerContent;
    @FXML
    public Button btnUpdate;
    public String customerId;
    UserNameMedia nameMedia;
    Customer customer = new Customer();
    CustomerGetway customerGetway = new CustomerGetway();
    CustomerBLL customerBLL = new CustomerBLL();
    @FXML
    private TextField tfCustomerName;
    @FXML
    private TextArea taCustomerContact;
    @FXML
    private TextArea taCustomerAddress;
    @FXML
    private Button btnClose;
    private String userId;
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        this.customer.customerName = this.tfCustomerName.getText().trim();
        this.customer.customerAddress = this.taCustomerAddress.getText().trim();
        this.customer.customerConNo = this.taCustomerContact.getText().trim();
        this.customer.userId = this.userId;
        this.customerBLL.save(this.customer);
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        this.customer.id = this.customerId;
        this.customer.customerName = this.tfCustomerName.getText().trim();
        this.customer.customerAddress = this.taCustomerAddress.getText().trim();
        this.customer.customerConNo = this.taCustomerContact.getText().trim();
        this.customerBLL.update(this.customer);
    }
    public void viewDetails() {
        this.customer.id = this.customerId;
        this.customerGetway.selectedView(this.customer);
        this.tfCustomerName.setText(this.customer.customerName);
        this.taCustomerContact.setText(this.customer.customerConNo);
        this.taCustomerAddress.setText(this.customer.customerAddress);
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\sell\AddCustomerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */