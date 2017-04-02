package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.dal.Supplyer;
import com.exuberant.ims.getway.SupplyerGetway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.util.ResourceBundle;
import static com.exuberant.ims.custom.EffectUtility.makeDraggable;
public class AddSupplyerController
        implements Initializable {
    public String supplyerId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Button btnClose;
    @FXML
    public Label lblCaption;
    Supplyer oSupplier = new Supplyer();
    SupplyerGetway supplyerGetway = new SupplyerGetway();
    private String usrId;
    private UserNameMedia media;
    @FXML
    private TextField tfSupplyerName;
    @FXML
    private TextArea taSupplyerAddress;
    @FXML
    private TextArea taSupplyerDescription;
    @FXML
    private TextArea taContactNumbers;
    private Stage primaryStage;
    @FXML
    private AnchorPane apContent;
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
    private void btnSaveOnAction(ActionEvent event) {
        if (isNotNull()) {
            this.oSupplier.supplyerName = this.tfSupplyerName.getText();
            this.oSupplier.supplyerContactNumber = this.taContactNumbers.getText();
            this.oSupplier.supplyerAddress = this.taSupplyerAddress.getText();
            this.oSupplier.supplyerDescription = this.taSupplyerDescription.getText();
            this.oSupplier.creatorId = this.usrId;
            this.supplyerGetway.save(this.oSupplier);
            clrAll();
        }
    }
    public boolean isNotNull() {
        boolean isNotNull;
        if ((this.tfSupplyerName.getText().trim().isEmpty()) ||
                (this.tfSupplyerName.getText().trim().isEmpty()) ||
                (this.taSupplyerAddress.getText().trim().isEmpty()) ||
                (this.taSupplyerAddress.getText().trim().isEmpty())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("ERROR : NULL FOUND");
            alert.setContentText("Please fill all require field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            isNotNull = false;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }
    private void clrAll() {
        this.tfSupplyerName.clear();
        this.taContactNumbers.clear();
        this.taSupplyerAddress.clear();
        this.taSupplyerDescription.clear();
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (isNotNull()) {
            this.oSupplier.id = this.supplyerId;
            this.oSupplier.supplyerName = this.tfSupplyerName.getText().trim();
            this.oSupplier.supplyerContactNumber = this.taContactNumbers.getText().trim();
            this.oSupplier.supplyerAddress = this.taSupplyerAddress.getText().trim();
            this.oSupplier.supplyerDescription = this.taSupplyerDescription.getText().trim();
            this.supplyerGetway.update(this.oSupplier);
        }
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnUpdate.getScene().getWindow();
        stage.close();
    }
    public void showDetails() {
        this.oSupplier.id = this.supplyerId;
        this.supplyerGetway.selectedView(this.oSupplier);
        this.tfSupplyerName.setText(this.oSupplier.supplyerName);
        this.taContactNumbers.setText(this.oSupplier.supplyerContactNumber);
        this.taSupplyerAddress.setText(this.oSupplier.supplyerAddress);
        this.taSupplyerDescription.setText(this.oSupplier.supplyerDescription);
    }
    @FXML
    private void apOnMouseDragged(MouseEvent event) {
    }
    @FXML
    private void apOnMousePressed(MouseEvent event) {
    }
    public void addSupplyerStage(Stage stage) {
        makeDraggable(stage, this.apContent);
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\stock\AddSupplyerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */