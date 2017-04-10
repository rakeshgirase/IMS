package com.exuberant.ims.controller;

import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController
        implements Initializable {
    Users users = new Users();
    CustomTf cTF = new CustomTf();
    CustomPf cPF = new CustomPf();

    String db = PropertyService.getInstance().getProperty("db");
    @FXML
    private TextField tfUserName;
    @FXML
    private Button btnUserNameTfClear;
    @FXML
    private Button btnPassFieldClear;
    @FXML
    private PasswordField pfUserPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink hlCrateAccount;
    @FXML
    private AnchorPane apMother;
    @FXML
    private AnchorPane apDesignPane;
    @FXML
    private Hyperlink hlDatabase;

    public void initialize(URL url, ResourceBundle rb) {
        this.cTF.clearTextFieldByButton(this.tfUserName, this.btnUserNameTfClear);
        this.cPF.clearPassFieldByButton(this.pfUserPassword, this.btnPassFieldClear);
        BooleanBinding boolenBinding = this.tfUserName.textProperty().isEmpty().or(this.pfUserPassword.textProperty().isEmpty());
        this.btnLogin.disableProperty().bind(boolenBinding);
    }

    @FXML
    private void hlCreateAnAccount(ActionEvent event) throws IOException {
        loadRegistration();
    }

    @FXML
    private void btnLogin(ActionEvent event)
            throws IOException {
        UserNameMedia media = new UserNameMedia();
        ApplicationController apController = new ApplicationController();
        FXMLLoader loader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("Application.fxml");
        loader.setLocation(resource);
        loader.load();
        Parent parent = (Parent) loader.getRoot();
        Scene adminPanelScene = new Scene(parent);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setMaximized(true);
        ApplicationController apControl = (ApplicationController) loader.getController();
        UserNameMedia usrNameMedia = new UserNameMedia(1L, users);
        apControl.setUsrNameMedia(usrNameMedia);
        apControl.btnHomeOnClick(event);
        apControl.permission();
        apControl.viewDetails();
        adminPanelStage.setScene(adminPanelScene);
        adminPanelStage.getIcons().add(new Image(URLService.getFileAsStream("image/icon.png")));
        adminPanelStage.setTitle("TITLE LOGIN");
        adminPanelStage.show();
        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
        stage.close();
    }

    private boolean isValidCondition() {
        boolean validCondition = false;
        if ((this.tfUserName.getText().trim().isEmpty()) ||
                (this.pfUserPassword.getText().isEmpty())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("WARNING :");
            alert.setHeaderText("WARNING !!");
            alert.setContentText("Please Fill Text Field And Password Properly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            validCondition = false;
        } else {
            validCondition = true;
        }
        return validCondition;
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
        try {
            btnLogin(event);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadRegistration() {
        Parent root = null;
        try {
            URL resource = URLService.getFileAsResoure("Registration.fxml");
            root = (Parent) FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(true);
            nStage.setTitle("Registration -com.exuberant.ims.storekeeper");
            nStage.show();
            Stage stage = (Stage) this.hlCrateAccount.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void hlDbOnAction(ActionEvent event) {
        Parent root = null;
        try {
            URL resource = URLService.getFileAsResoure("Server.fxml");
            root = (Parent) FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setMaximized(false);
            nStage.setTitle("Server Status -com.exuberant.ims.storekeeper");
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
