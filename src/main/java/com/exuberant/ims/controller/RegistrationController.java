package com.exuberant.ims.controller;

import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.getway.UserGateway;
import com.exuberant.ims.storekeeper.URLService;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class RegistrationController
        implements Initializable {
    Users users = new Users();
    UserGateway userGateway = new UserGateway();

    @FXML
    private Hyperlink hlLogin;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private PasswordField pfUserPassword;
    @FXML
    private PasswordField pfReUserPassword;
    @FXML
    private Button btnClearUserName;
    @FXML
    private Button btnClearFullName;
    @FXML
    private Button btnClearPass;
    @FXML
    private Button btnClearRePass;
    @FXML
    private Button btnSignUp;
    private Stage stage;
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    public void initialize(URL url, ResourceBundle rb) {
        CustomPf cPF = new CustomPf();
        CustomTf cTF = new CustomTf();
        cTF.clearTextFieldByButton(this.tfUserName, this.btnClearUserName);
        cTF.clearTextFieldByButton(this.tfFullName, this.btnClearFullName);
        cPF.clearPassFieldByButton(this.pfUserPassword, this.btnClearPass);
        cPF.clearPassFieldByButton(this.pfReUserPassword, this.btnClearRePass);
        BooleanBinding boolenBinding = this.tfUserName.textProperty().isEmpty().or(this.tfFullName.textProperty().isEmpty()
                .or(this.pfUserPassword.textProperty().isEmpty())
                .or(this.pfReUserPassword.textProperty().isEmpty()));
        this.btnSignUp.disableProperty().bind(boolenBinding);
    }
    @FXML
    private void hlLogin(ActionEvent event) throws IOException {
        URL resource = URLService.getFileAsResoure("Login.fxml");
        Parent root = (Parent) FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setMaximized(true);
        nStage.setTitle("Registration -com.exuberant.ims.storekeeper");
        nStage.show();
        Stage hlLoginStage = (Stage) this.hlLogin.getScene().getWindow();
        hlLoginStage.close();
    }
    @FXML
    private void btnRegistration(ActionEvent event) {
        if (isValidCondition()) {
            this.users.setUserName(this.tfUserName.getText());
            this.users.setFullName(this.tfUserName.getText());
            this.users.setPassword(this.pfUserPassword.getText());
            this.userGateway.save(this.users);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Login now");
            alert.setContentText("You admin account has been create sucessfully \n to login now click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                try {
                    hlLogin(event);
                } catch (IOException ex) {
                    Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private boolean isValidCondition() {
        boolean registration = false;
        if ((nullChecq()) && (passMatch())) {
            System.out.println("Condition valid");
            registration = true;
        } else {
            System.out.println("Condition Invalid");
            registration = false;
        }
        return registration;
    }
    private boolean nullChecq() {
        boolean nullChecq = false;
        if ((this.tfUserName.getText().trim().isEmpty()) ||
                (this.tfFullName.getText().trim().isEmpty()) ||
                (this.pfUserPassword.getText().isEmpty()) ||
                (this.pfReUserPassword.getText().isEmpty())) {
            System.out.println("Empty users Name");
            nullChecq = false;
        } else {
            System.out.println("Users Name not Empty");
            nullChecq = true;
        }
        return nullChecq;
    }
    private boolean passMatch() {
        boolean passMatch = false;
        String password = this.pfUserPassword.getText();
        String rePass = this.pfReUserPassword.getText();
        if (password.matches(rePass)) {
            System.out.println("Password Match");
            passMatch = true;
        } else {
            System.out.println("Password Not Match");
            passMatch = false;
        }
        return passMatch;
    }
    @FXML
    private void pfKeyTyped(KeyEvent event) {
        if (this.pfUserPassword.getText().matches(this.pfReUserPassword.getText())) {
            System.out.println("Match");
        } else {
            System.out.println("Not Match");
        }
    }
}
