package com.exuberant.ims.controller.application;

import com.exuberant.ims.controller.application.settings.MyAccountController;
import com.exuberant.ims.controller.application.settings.OrgSettingController;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController
        implements Initializable {
    @FXML
    public BorderPane bpSettings;

    String db = PropertyService.getInstance().getProperty("db");
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
    private Long userID;

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
        URL resource = URLService.getFileAsResoure("application/settings/MyAccount.fxml");
        fxmlload.load(resource.openStream());
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
        URL resource = URLService.getFileAsResoure("application/settings/OrgSetting.fxml");
        fXMLLoader.load(resource.openStream());
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

    }
}
