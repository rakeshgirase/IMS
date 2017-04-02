package com.exuberant.ims.controller.application;
import com.exuberant.ims.controller.application.settings.MyAccountController;
import com.exuberant.ims.controller.application.settings.OrgSettingController;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SettingsController
        implements Initializable {
    @FXML
    public BorderPane bpSettings;
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
    UserNameMedia usrMedia;
    @FXML
    private MenuItem miMyASccount;
    @FXML
    private MenuItem miOrganize;
    @FXML
    private MenuItem miBackup;
    @FXML
    private StackPane spSettingContent;
    @FXML
    private Label lblCurrentStatus;
    private String userID;
    public UserNameMedia getUsrMedia() {
        return this.usrMedia;
    }
    public void setUsrMedia(UserNameMedia usrMedia) {
        this.userID = usrMedia.getId();
        this.usrMedia = usrMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    public void miMyASccountOnClick(ActionEvent event)
            throws IOException {
        System.out.println(this.userID);
        this.lblCurrentStatus.setText("My Account");
        MyAccountController myAccount = new MyAccountController();
        UserNameMedia usrIdMedia = new UserNameMedia();
        FXMLLoader fxmlload = new FXMLLoader();
        fxmlload.load(getClass().getResource("/view/application/settings/MyAccount.fxml").openStream());
        usrIdMedia.setId(this.userID);
        MyAccountController mAccount = (MyAccountController) fxmlload.getController();
        mAccount.setUsrMediaID(this.usrMedia);
        mAccount.showDetails();
        AnchorPane acPane = (AnchorPane) fxmlload.getRoot();
        this.spSettingContent.getChildren().clear();
        this.spSettingContent.getChildren().add(acPane);
    }
    @FXML
    private void miOrganizeOnAction(ActionEvent event) throws IOException {
        System.out.println(this.userID);
        this.lblCurrentStatus.setText("Org. Setup");
        OrgSettingController orgSetting = new OrgSettingController();
        UserNameMedia useridMedia = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/settings/OrgSetting.fxml").openStream());
        useridMedia.setId(this.userID);
        OrgSettingController orgnizeUsrId = (OrgSettingController) fXMLLoader.getController();
        orgnizeUsrId.setUsrIdMedia(useridMedia);
        orgnizeUsrId.showDetails();
        this.spSettingContent.getChildren().clear();
        AnchorPane orgAp = (AnchorPane) fXMLLoader.getRoot();
        this.spSettingContent.getChildren().add(orgAp);
    }
    @FXML
    private void miBackupOnAction(ActionEvent event) {
    }
    public void settingPermission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where id=?");
            this.pst.setString(1, this.userID);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt(18) == 0) {
                    this.miOrganize.setDisable(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\SettingsController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */