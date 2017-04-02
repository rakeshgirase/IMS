package com.exuberant.ims.controller.application.settings;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.DBProperties;
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
import com.exuberant.ims.media.UserNameMedia;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OrgSettingController
        implements Initializable {
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = this.dBProperties.loadPropertiesFile();
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
    private String userId;
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
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Organize where Id=?");
            this.pst.setString(1, "1");
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                this.tfOrganizeName.setText(this.rs.getString(2));
                this.tfWebSite.setText(this.rs.getString(3));
                this.taContactNumber.setText(this.rs.getString(4));
                this.taAdddress.setText(this.rs.getString(5));
                Blob blob = this.rs.getBlob(6);
                if (blob != null) {
                    ByteArrayInputStream in = new ByteArrayInputStream(blob.getBytes(1L, (int) blob.length()));
                    this.image = new Image(in);
                    this.retOrgLogo.setFill(new ImagePattern(this.image));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean isFoundData() {
        boolean dataFound = true;
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".Organize ORDER BY Id ASC LIMIT 1");
            this.rs = this.pst.executeQuery();
            if (this.rs.next()) {
                System.out.println("Data Found");
                return dataFound;
            }
            System.out.println("Data not found");
            dataFound = false;
        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataFound;
    }
    private void updateOrganizeWithImage() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("Update " + this.db + ".Organize set OrgName=?,OrgWeb=?,OrgContactNumbers=?,OrgContactAddress=?,OrgLogo=? where Id=1");
            this.pst.setString(1, this.tfOrganizeName.getText());
            this.pst.setString(2, this.tfWebSite.getText());
            this.pst.setString(3, this.taContactNumber.getText());
            this.pst.setString(4, this.taAdddress.getText());
            if (this.imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(this.imagePath));
                    this.pst.setBlob(5, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.pst.setBlob(5, (Blob) null);
            }
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Update ");
            alert.setContentText("Update Sucessfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void insertOrganizeWithImage() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("insert into " + this.db + ".Organize values(?,?,?,?,?,?,?)");
            this.pst.setString(1, "1");
            this.pst.setString(2, this.tfOrganizeName.getText());
            this.pst.setString(3, this.tfWebSite.getText());
            this.pst.setString(4, this.taContactNumber.getText());
            this.pst.setString(5, this.taAdddress.getText());
            if (this.imagePath != null) {
                try {
                    InputStream is = new FileInputStream(new File(this.imagePath));
                    this.pst.setBlob(6, is);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.pst.setBlob(6, (Blob) null);
            }
            this.pst.setString(7, this.userId);
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Sucess ");
            alert.setContentText("Insert Data Sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void updateOrganizeWithOutImage() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("Update " + this.db + ".Organize set OrgName=?,OrgWeb=?,OrgContactNumbers=?,OrgContactAddress=? where Id=1");
            this.pst.setString(1, this.tfOrganizeName.getText());
            this.pst.setString(2, this.tfWebSite.getText());
            this.pst.setString(3, this.taContactNumber.getText());
            this.pst.setString(4, this.taAdddress.getText());
            this.pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Sucess ");
            alert.setContentText("Update sucessfuly");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(OrgSettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\settings\OrgSettingController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */