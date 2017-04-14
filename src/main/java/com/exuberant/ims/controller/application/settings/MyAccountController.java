package com.exuberant.ims.controller.application.settings;

import com.exuberant.ims.dal.Users;
import com.exuberant.ims.getway.UserGateway;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.beans.binding.BooleanBinding;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyAccountController
        implements Initializable {
    Users users = new Users();
    UserGateway userGateway = new UserGateway();

    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfContractNumber;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextArea taAddress;
    @FXML
    private Button btnSave;
    @FXML
    private Hyperlink hlChangePassword;
    @FXML
    private Rectangle retImage;
    private Image image;
    private File file;
    private FileInputStream fileInput;
    private FileOutputStream fileOutput;
    private byte[] userImage;
    private String imgPath;
    private Long userID;
    private UserNameMedia usrMediaID;
    @FXML
    private AnchorPane apMyAccountMother;
    @FXML
    private Button attachImage;

    public UserNameMedia getUsrMediaID() {
        return this.usrMediaID;
    }

    public void setUsrMediaID(UserNameMedia usrMediaID) {
        this.userID = usrMediaID.getId();
        this.usrMediaID = usrMediaID;
    }

    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding boolenBinding = this.tfFullName.textProperty().isEmpty().or(this.tfEmailAddress.textProperty().isEmpty()
                .or(this.tfContractNumber.textProperty().isEmpty()));
        this.btnSave.disableProperty().bind(boolenBinding);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        this.users.setUserName(this.tfUserName.getText());
        this.users.setFullName(this.tfFullName.getText());
        this.users.setEmailAddress(this.tfEmailAddress.getText());
        this.users.setAddress(this.taAddress.getText());
        this.users.setContactNumber(this.tfContractNumber.getText());
        this.users.setImagePath(this.imgPath);
        //this.users.image = this.image;
        this.userGateway.update(this.users);
    }

    @FXML
    private void hlChangePasswordOnClick(ActionEvent event)
            throws IOException {
        System.out.println("You can change your password");
        ChangePasswordController pcc = new ChangePasswordController();
        UserNameMedia nameMedia = new UserNameMedia();
        FXMLLoader loader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/settings/PasswordChange.fxml");
        loader.setLocation(resource);
        loader.load();
        Parent root = (Parent) loader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
        ChangePasswordController changePasswordController = (ChangePasswordController) loader.getController();
        nameMedia.setId(this.userID);
        changePasswordController.setNameMedia(nameMedia);
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.setTitle("Registration -com.exuberant.ims.storekeeper");
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }

    @FXML
    private void apOnOpenAction(MouseEvent event) {
    }

    public void showDetails() {
        this.users.setId(this.userID);
        this.userGateway.selectedView(this.users);
        this.tfUserName.setText(this.users.getUserName());
        this.tfFullName.setText(this.users.getFullName());
        this.tfContractNumber.setText(this.users.getContactNumber());
        this.tfEmailAddress.setText(this.users.getEmailAddress());
        this.taAddress.setText(this.users.getAddress());
        //this.image = this.users.image;
        this.retImage.setFill(new ImagePattern(this.image));
    }

    public void accountPermission() {

    }

    @FXML
    private void attachImageOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        ExtensionFilter extFilterjpg = new ExtensionFilter("jpg files (*.jpg)", new String[]{"*.jpg"});
        ExtensionFilter extFilterpng = new ExtensionFilter("png files (*.png)", new String[]{"*.png"});
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{extFilterjpg, extFilterpng});
        this.file = fileChooser.showOpenDialog(null);
        if (this.file != null) {
            if (this.file.length() < 6000000L) {
                System.out.print("Condition ok");
                System.out.println(this.file.length());
                BufferedImage bufferedImage = ImageIO.read(this.file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.retImage.setFill(new ImagePattern(image));
                this.imgPath = this.file.getAbsolutePath();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Permission");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Ioomage file is too big to upload \nplease chanother view.image");
                alert.initStyle(StageStyle.UNDECORATED);
            }
        }
    }

    private boolean nullCheck() {
        boolean notNull = false;
        if ((this.tfFullName.getText().trim().length() == 0) ||
                (this.tfContractNumber.getText().trim().length() == 0) ||
                (this.tfEmailAddress.getText().trim().length() == 0)) {
            notNull = false;
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR ");
            alert.setContentText("Please Fill all requere fields");
            alert.initStyle(StageStyle.UNDECORATED);
        } else {
            notNull = true;
            System.out.println("Not Null");
        }
        return notNull;
    }
}
