package com.exuberant.ims.controller.popup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
public class SucessController
        implements Initializable {
    @FXML
    public Button btnClose;
    @FXML
    public Text message;
    @FXML
    private ImageView ivSucessImage;
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\popup\SucessController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */