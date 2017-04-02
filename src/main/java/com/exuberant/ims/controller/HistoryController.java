package com.exuberant.ims.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class HistoryController
        implements Initializable {
    @FXML
    public TextArea tfHistory;
    @FXML
    private Label lblHistory;
    @FXML
    private Button btnClose;
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    public void showData() {
    }
}
