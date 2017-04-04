package com.exuberant.ims.controller.application.employee;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.getway.UsersGetway;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.util.PropertyService;
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
import com.exuberant.ims.media.UserNameMedia;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    String db = PropertyService.getInstance().getProperty("db");
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
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
    private String usrId;
    private UserNameMedia nameMedia;
    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }
    public void setNameMedia(UserNameMedia nameMedia) {
        this.usrId = nameMedia.getId();
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
        this.users.userName = this.tfUserName.getText();
        this.users.fullName = this.tfFullName.getText();
        this.users.emailAddress = this.tfEmail.getText();
        this.users.contactNumber = this.tfPhone.getText();
        this.users.salary = this.tfSalary.getText();
        this.users.address = this.taAddress.getText();
        this.users.password = this.tfPassword.getText();
        this.users.imagePath = this.imagePath;
        this.users.creatorId = this.usrId;
        this.usersGetway.save(this.users);
        basicPermission();
    }
    private void basicPermission() {
        DBConnection dbCon = new DBConnection();
        Connection con = dbCon.getConnection();
        try {
            PreparedStatement pst = con.prepareStatement("Select Id FROM " + this.db + ".User where UsrName=?");
            pst.setString(1, this.tfUserName.getText());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pst = con.prepareStatement("insert into " + this.db + ".UserPermission values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setInt(2, 0);
                pst.setInt(3, 0);
                pst.setInt(4, 0);
                pst.setInt(5, 0);
                pst.setInt(6, 0);
                pst.setInt(7, 0);
                pst.setInt(8, 0);
                pst.setInt(9, 0);
                pst.setInt(10, 0);
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setInt(14, 0);
                pst.setInt(15, 0);
                pst.setInt(16, 0);
                pst.setInt(17, 0);
                pst.setInt(18, 0);
                pst.setInt(19, 0);
                pst.setInt(20, rs.getInt("Id"));
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
