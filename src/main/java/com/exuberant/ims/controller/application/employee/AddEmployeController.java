package com.exuberant.ims.controller.application.employee;

import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.dal.User;
import com.exuberant.ims.getway.UserGateway;
import com.exuberant.ims.media.UserNameMedia;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeController
        implements Initializable {
    @FXML
    public Button btnClrUsrName;
    @FXML
    public Button btnClrFullName;
    @FXML
    public Button btnClrEmail;
    @FXML
    public Button btnClrPhone;
    @FXML
    public Button btnClrSalary;
    @FXML
    public Button btnClrPassword;

    UserGateway userGateway = new UserGateway();
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnSave;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextArea taAddress;
    @FXML
    private ImageView imvUsrImg;
    private File file;
    private BufferedImage bufferedImage;
    private Image image;
    private String imagePath;
    private User user;
    private UserNameMedia nameMedia;

    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }

    public void setNameMedia(UserNameMedia nameMedia) {
        this.user = nameMedia.getUser();
        this.nameMedia = nameMedia;
    }

    public void initialize(URL url, ResourceBundle rb) {
        CustomTf cTf = new CustomTf();
        cTf.clearTextFieldByButton(this.tfUserName, this.btnClrUsrName);
        cTf.clearTextFieldByButton(this.tfFullName, this.btnClrFullName);
        cTf.clearTextFieldByButton(this.tfEmail, this.btnClrEmail);
        cTf.clearTextFieldByButton(this.tfPhone, this.btnClrPhone);
        cTf.clearTextFieldByButton(this.tfSalary, this.btnClrSalary);
        cTf.clearTextFieldByButton(this.tfPassword, this.btnClrPassword);
        cTf.numaricTextfield(this.tfSalary);
        BooleanBinding binding = this.tfUserName.textProperty().isEmpty().or(this.tfFullName.textProperty().isEmpty()
                .or(this.tfPhone.textProperty().isEmpty())
                .or(this.tfPassword.textProperty().isEmpty()));
        this.btnSave.disableProperty().bind(binding);
    }

    @FXML
    private void btnAttachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("JPG (Joint Photographic Group)", new String[]{"*.jpg"}), new ExtensionFilter("JPEG (Joint Photographic Experts Group)", new String[]{"*.jpeg"}), new ExtensionFilter("PNG (Portable Network Graphics)", new String[]{"*.png"})});
        fileChooser.setTitle("Choise a Image File");
        this.file = fileChooser.showOpenDialog(null);
        if (this.file != null) {
            System.out.println(this.file);
            this.bufferedImage = ImageIO.read(this.file);
            this.image = SwingFXUtils.toFXImage(this.bufferedImage, null);
            this.imvUsrImg.setImage(this.image);
            this.imagePath = this.file.getAbsolutePath();
        }
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        this.user.setUserName(this.tfUserName.getText());
        this.user.setFullName(this.tfFullName.getText());
        this.user.setEmailAddress(this.tfEmail.getText());
        this.user.setContactNumber(this.tfPhone.getText());
        this.user.setSalary(this.tfSalary.getText());
        this.user.setAddress(this.taAddress.getText());
        this.user.setPassword(this.tfPassword.getText());
        this.user.setImagePath(this.imagePath);
        this.user.setCreatorId(this.user.getId());
        this.userGateway.save(this.user);
        basicPermission();
    }

    private void basicPermission() {

    }
}
