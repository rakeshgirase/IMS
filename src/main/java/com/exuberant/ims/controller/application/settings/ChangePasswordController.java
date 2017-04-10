package com.exuberant.ims.controller.application.settings;
import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.media.UserNameMedia;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;
public class ChangePasswordController
        implements Initializable {
    Users users = new Users();
    CustomPf customPf = new CustomPf();

    @FXML
    private PasswordField pfCurrentPass;
    @FXML
    private PasswordField pfNewPass;
    @FXML
    private PasswordField pfRePass;
    @FXML
    private Button btnClrCurrentPf;
    @FXML
    private Button btnClrRePass;
    @FXML
    private Button btnClrNewPass;
    @FXML
    private Button btnChangePass;
    @FXML
    private Button btnClose;
    private UserNameMedia nameMedia;
    @FXML
    private ImageView imgCurrentPassMatch;
    @FXML
    private ImageView imgNewPassMatch;
    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }
    public void setNameMedia(UserNameMedia nameMedia) {
        this.users = nameMedia.getUsers();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
        this.customPf.clearPassFieldByButton(this.pfCurrentPass, this.btnClrCurrentPf);
        this.customPf.clearPassFieldByButton(this.pfNewPass, this.btnClrNewPass);
        this.customPf.clearPassFieldByButton(this.pfRePass, this.btnClrRePass);
        BooleanBinding binding = this.pfCurrentPass.textProperty().isEmpty().or(this.pfNewPass.textProperty().isEmpty()
                .or(this.pfRePass.textProperty().isEmpty()));
        this.btnChangePass.disableProperty().bind(binding);
    }
    @FXML
    private void btnChangePassOnAction(ActionEvent event) {
        if (isCurrentPasswordChecqOk()) {
            if (isPasswordMatch()) {
                updatePassword();
            }
        } else {
            System.out.println("ddd");
        }
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void pfOnAction(ActionEvent event) {
        btnChangePassOnAction(event);
    }
    @FXML
    private void pfNewPassWordMatch(KeyEvent event) {
        if (this.pfNewPass.getText().matches(this.pfRePass.getText())) {
            System.out.println("Match");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Invalid password");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
    }
    private boolean isCurrentPasswordChecqOk() {
        boolean conDitionValid = true;
        return conDitionValid;
    }
    private boolean isPasswordMatch() {
        boolean passMatch;
        if (this.pfNewPass.getText().matches(this.pfRePass.getText())) {
            System.out.println("New Password match");
            passMatch = true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("New Password what you enterd are not matched");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            passMatch = false;
        }
        return passMatch;
    }
    private void updatePassword() {

    }
}
