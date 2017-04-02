package com.exuberant.ims.storekeeper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
public class FXMLDocumentController
        implements Initializable {
    @FXML
    private Label label;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        this.label.setText("Hello World!");
    }
    public void initialize(URL url, ResourceBundle rb) {
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.storekeeper\FXMLDocumentController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */