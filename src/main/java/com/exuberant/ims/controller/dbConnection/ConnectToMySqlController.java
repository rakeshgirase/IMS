package com.exuberant.ims.controller.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
public class ConnectToMySqlController
        implements Initializable {
    @FXML
    private TextField tfHostName;
    @FXML
    private TextField tfPortName;
    @FXML
    private TextField tfUserName;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnSave;
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\dbConnection\ConnectToMySqlController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */