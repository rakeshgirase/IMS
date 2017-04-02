package com.exuberant.ims.controller.application.settings;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.util.PropertyService;
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
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PassChangeController
        implements Initializable {
    Users users = new Users();
    CustomPf customPf = new CustomPf();
    DBConnection dbCon = new DBConnection();
    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    String db = PropertyService.getInstance().getProperty("db");
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
    private String userId;
    private String userName;
    private UserNameMedia nameMedia;
    @FXML
    private ImageView imgCurrentPassMatch;
    @FXML
    private ImageView imgNewPassMatch;
    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.userName = nameMedia.getUserName();
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
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from User where Id=? and Password=?");
            this.pst.setString(1, this.userId);
            this.pst.setString(2, this.pfCurrentPass.getText());
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("Old Password Match");
                return conDitionValid;
            }
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Invalid password");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            conDitionValid = false;
        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("Update " + this.db + ".User set Password=? where Id=?");
            this.pst.setString(1, this.pfNewPass.getText());
            this.pst.setString(2, this.userId);
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Sucess ");
            alert.setContentText("Update Password Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(PassChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
