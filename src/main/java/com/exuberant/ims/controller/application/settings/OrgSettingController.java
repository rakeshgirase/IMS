package com.exuberant.ims.controller.application.settings;
import com.exuberant.ims.media.UserNameMedia;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class OrgSettingController
        implements Initializable {

    @FXML
    private TextField tfOrganizeName;
    @FXML
    private Rectangle retOrgLogo;
    @FXML
    private Button btnAttechLogo;
    @FXML
    private Button btnSaveOrganize;
    private File file;
    private BufferedImage bufferedImage;
    private Image image;
    private Long userId;
    private String imagePath;
    private UserNameMedia usrIdMedia;
    @FXML
    private TextField tfWebSite;
    @FXML
    private TextArea taContactNumber;
    @FXML
    private TextArea taAdddress;
    public UserNameMedia getUsrIdMedia() {
        return this.usrIdMedia;
    }
    public void setUsrIdMedia(UserNameMedia usrIdMedia) {
        this.userId = usrIdMedia.getId();
        this.usrIdMedia = usrIdMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBind = this.tfOrganizeName.textProperty().isEmpty().or(this.tfWebSite.textProperty().isEmpty()
                .or(this.taContactNumber.textProperty().isEmpty())
                .or(this.taAdddress.textProperty().isEmpty()));
        this.btnSaveOrganize.disableProperty().bind(boolenBind);
    }
    @FXML
    private void btnSaveOrganizeOnClick(ActionEvent event) {
        if (isFoundData()) {
            if (this.imagePath != null) {
                updateOrganizeWithImage();
            } else {
                updateOrganizeWithOutImage();
            }
        } else {
            insertOrganizeWithImage();
        }
    }
    @FXML
    private void btnAttechLogoOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("JPG (Joint Photographic Group)", new String[]{"*.jpg"}), new ExtensionFilter("JPEG (Joint Photographic Experts Group)", new String[]{"*.jpeg"}), new ExtensionFilter("PNG (Portable Network Graphics)", new String[]{"*.png"})});
        fileChooser.setTitle("Choise a Image File");
        this.file = fileChooser.showOpenDialog(null);
        if (this.file != null) {
            System.out.println(this.file);
            this.bufferedImage = ImageIO.read(this.file);
            this.image = SwingFXUtils.toFXImage(this.bufferedImage, null);
            this.retOrgLogo.setFill(new ImagePattern(this.image));
            this.imagePath = this.file.getAbsolutePath();
        }
    }
    public void showDetails() {
    }
    private boolean isFoundData() {
        boolean dataFound = true;
        return dataFound;
    }
    private void updateOrganizeWithImage() {

    }
    private void insertOrganizeWithImage() {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Sucess ");
            alert.setContentText("Insert Data Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
    }

    private void updateOrganizeWithOutImage() {

    }
}
