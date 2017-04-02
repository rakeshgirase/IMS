package com.exuberant.ims.controller.application.settings;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.getway.UsersGetway;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
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
import com.exuberant.ims.media.UserNameMedia;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MyAccountController
        implements Initializable {
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
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
    private String userID;
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
        this.users.userName = this.tfUserName.getText();
        this.users.fullName = this.tfFullName.getText();
        this.users.emailAddress = this.tfEmailAddress.getText();
        this.users.address = this.taAddress.getText();
        this.users.contactNumber = this.tfContractNumber.getText();
        this.users.imagePath = this.imgPath;
        this.users.image = this.image;
        this.usersGetway.update(this.users);
    }
    @FXML
    private void hlChangePasswordOnClick(ActionEvent event)
            throws IOException {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where id=?");
            this.pst.setString(1, this.userID);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt("ChangeOwnPass") != 0) {
                    System.out.println("You can change your password");
                    PassChangeController pcc = new PassChangeController();
                    UserNameMedia nameMedia = new UserNameMedia();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/application/settings/PassChange.fxml"));
                    loader.load();
                    Parent root = (Parent) loader.getRoot();
                    Scene scene = new Scene(root);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    PassChangeController passChangeController = (PassChangeController) loader.getController();
                    nameMedia.setId(this.userID);
                    passChangeController.setNameMedia(nameMedia);
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.setTitle("Registration -com.exuberant.ims.storekeeper");
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Permiss");
                    alert.setHeaderText("Permission denied");
                    alert.setContentText("You are no longer to make change your password");
                    alert.initStyle(StageStyle.UNDECORATED);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.con);
    }
    @FXML
    private void apOnOpenAction(MouseEvent event) {
    }
    public void showDetails() {
        this.users.id = this.userID;
        this.usersGetway.selectedView(this.users);
        this.tfUserName.setText(this.users.userName);
        this.tfFullName.setText(this.users.fullName);
        this.tfContractNumber.setText(this.users.contactNumber);
        this.tfEmailAddress.setText(this.users.emailAddress);
        this.taAddress.setText(this.users.address);
        this.image = this.users.image;
        this.retImage.setFill(new ImagePattern(this.image));
    }
    public void accountPermission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where UserId=?");
        } catch (SQLException ex) {
            Logger.getLogger(MyAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                alert.setTitle("Permiss");
                alert.setHeaderText("Permission denied");
                alert.setContentText("Your Image file is too big to upload \nplease choise another com.exuberant.ims.image");
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
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\settings\MyAccountController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */