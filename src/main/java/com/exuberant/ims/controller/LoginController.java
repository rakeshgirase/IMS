package com.exuberant.ims.controller;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.database.DBConnection;

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
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
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
        DBConnection dbCon = new DBConnection();
        this.con = dbCon.getConnection();
        if (this.con != null) {
            try {
                this.pst = this.con.prepareStatement("SELECT Id FROM " + this.db + ".User ORDER BY Id ASC LIMIT 1");
                this.rs = this.pst.executeQuery();
                if (this.rs.next()) {
                    this.apMother.setOpacity(0.7D);
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("You can't create an account without admin \n permission");
                    alert.initStyle(StageStyle.UNDECORATED);
                    Optional<ButtonType> result = alert.showAndWait();
                    if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                        this.apMother.setOpacity(1.0D);
                    }
                    return;
                }
                this.con.close();
                this.pst.close();
                this.rs.close();
                loadRegistration();
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error : Server Not Found");
        alert.setContentText("Make sure your mysql is Start properly, \n");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }
    @FXML
    private void btnLogin(ActionEvent event)
            throws IOException {
        DBConnection dbCon = new DBConnection();
        this.con = dbCon.getConnection();
        if (this.con != null) {
            UserNameMedia media = new UserNameMedia();
            ApplicationController apController = new ApplicationController();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Application.fxml"));
            loader.load();
            Parent parent = (Parent) loader.getRoot();
            Scene adminPanelScene = new Scene(parent);
            Stage adminPanelStage = new Stage();
            adminPanelStage.setMaximized(true);
            if (isValidCondition()) {
                try {
                    this.pst = this.con.prepareStatement("select * from " + this.db + ".User where UsrName=? and Password=? and Status=1");
                    this.pst.setString(1, this.tfUserName.getText());
                    this.pst.setString(2, this.pfUserPassword.getText());
                    this.rs = this.pst.executeQuery();
                    if (this.rs.next()) {
                        UserNameMedia usrNameMedia = new UserNameMedia(this.rs.getString(1), this.rs.getString(2));
                        ApplicationController apControl = (ApplicationController) loader.getController();
                        apControl.setUsrNameMedia(usrNameMedia);
                        apControl.btnHomeOnClick(event);
                        apControl.permission();
                        apControl.viewDetails();
                        adminPanelStage.setScene(adminPanelScene);
                        adminPanelStage.getIcons().add(new Image("/com/exuberant/ims/image/icon.png"));
                        adminPanelStage.setTitle(this.rs.getString(3));
                        adminPanelStage.show();
                        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
                        stage.close();
                    } else {
                        System.out.println("Password Not Match");
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Password Not Match");
                        alert.setHeaderText("Error : Name or Pass Not matched");
                        alert.setContentText("User Name or Password not matched \ntry Again");
                        alert.initStyle(StageStyle.UNDECORATED);
                        alert.showAndWait();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error : Server Not Found");
            alert.setContentText("Make sure your mysql is Start properly, \n");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        }
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
            root = (Parent) FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
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
            root = (Parent) FXMLLoader.load(getClass().getResource("/view/Server.fxml"));
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
