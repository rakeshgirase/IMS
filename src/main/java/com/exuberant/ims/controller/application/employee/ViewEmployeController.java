package com.exuberant.ims.controller.application.employee;

import com.exuberant.ims.custom.CustomPf;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.custom.History;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.database.DBConnection;
import com.exuberant.ims.database.SQL;
import com.exuberant.ims.getway.UsersGetway;
import com.exuberant.ims.list.ListEmployee;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ViewEmployeController
        implements Initializable {
    @FXML
    public Button btnClrFulNametf;
    @FXML
    public Button btnClrEmailtf;
    @FXML
    public Button btnClrPhonetf;
    @FXML
    public Button btnClrSalarytf;
    @FXML
    public Button btnClrDatestf;
    @FXML
    public Button btnClrCreatortf;
    CustomPf cPf = new CustomPf();
    CustomTf cTf = new CustomTf();
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();

    String db = PropertyService.getInstance().getProperty("db");
    Connection con = this.dbCon.getConnection();
    PreparedStatement pst;
    ResultSet rs;
    Image usrImg = new Image("/com/exuberant/ims/image/rifat.jpg");
    private File file;
    private BufferedImage bufferedImage;
    private String imagePath;
    private Image image;
    private Blob blobImage;
    private String userId;
    private String name;
    private String id;
    private UserNameMedia nameMedia;
    private String creatorId;
    private String creatorName;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmailAddress;
    @FXML
    private TextField tfPhoneNumber;
    @FXML
    private TextField tfSearch;
    @FXML
    private Rectangle recUsrImage;
    @FXML
    private Button btnAttachImage;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfDateofJoin;
    @FXML
    private TextField tfCreatedBy;
    @FXML
    private TextArea taAddress;
    @FXML
    private CheckBox cbStatus;
    @FXML
    private Hyperlink hlChangePassword;
    @FXML
    private Hyperlink hlViewPermission;
    @FXML
    private TableView<ListEmployee> tblEmoyeeList;
    @FXML
    private TableColumn<Object, Object> clmEmployeId;
    @FXML
    private TableColumn<Object, Object> clmEmployeName;
    @FXML
    private Label lblCreator;
    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }
    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.name = nameMedia.getUserName();
        this.nameMedia = nameMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
        this.cTf.clearTextFieldByButton(this.tfFullName, this.btnClrFulNametf);
        this.cTf.clearTextFieldByButton(this.tfEmailAddress, this.btnClrEmailtf);
        this.cTf.clearTextFieldByButton(this.tfPhoneNumber, this.btnClrPhonetf);
        this.cTf.clearTextFieldByButton(this.tfDateofJoin, this.btnClrDatestf);
        this.cTf.clearTextFieldByButton(this.tfCreatedBy, this.btnClrCreatortf);
        this.cTf.clearTextFieldByButton(this.tfSalary, this.btnClrSalarytf);
        this.cTf.numaricTextfield(this.tfSalary);
    }
    @FXML
    private void tfSearchOnAction(ActionEvent event) {
    }
    @FXML
    private void btnAttachImageOnAction(ActionEvent event)
            throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter[]{new ExtensionFilter("JPG (Joint Photographic Group)", new String[]{"*.jpg"}), new ExtensionFilter("JPEG (Joint Photographic Experts Group)", new String[]{"*.jpeg"}), new ExtensionFilter("PNG (Portable Network Graphics)", new String[]{"*.png"})});
        fileChooser.setTitle("Choise a Image File");
        this.file = fileChooser.showOpenDialog(null);
        if (this.file != null) {
            System.out.println(this.file);
            this.bufferedImage = ImageIO.read(this.file);
            this.image = SwingFXUtils.toFXImage(this.bufferedImage, null);
            this.recUsrImage.setFill(new ImagePattern(this.image));
            this.imagePath = this.file.getAbsolutePath();
        }
    }
    @FXML
    private void tblViewOnClick(KeyEvent event) {
        if (event.getCode().equals(KeyCode.UP)) {
            setselectedView();
        } else if (event.getCode().equals(KeyCode.DOWN)) {
            setselectedView();
        }
    }
    public void tblEmloyeOnClick(Event event) {
        setselectedView();
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws FileNotFoundException {
        this.users.userName = this.tfUserName.getText();
        this.users.fullName = this.tfFullName.getText();
        this.users.emailAddress = this.tfEmailAddress.getText();
        this.users.contactNumber = this.tfPhoneNumber.getText();
        this.users.salary = this.tfSalary.getText();
        this.users.address = this.taAddress.getText();
        this.users.image = this.usrImg;
        if (this.cbStatus.isSelected()) {
            this.users.status = "1";
        } else {
            this.users.status = "0";
        }
        this.users.imagePath = this.imagePath;
        this.users.creatorId = this.userId;
        this.usersGetway.update(this.users);
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Employee");
        alert.setHeaderText("Are You sure ?");
        alert.setContentText("Are you sure to remove this employee \n Click OK to confirm");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            this.usersGetway.selectedView(this.users);
            this.usersGetway.delete(this.users);
        }
        this.tblEmoyeeList.getItems().clear();
        showDetails();
    }
    @FXML
    private void cbOnAction(ActionEvent event) {
        if (this.cbStatus.isSelected()) {
            this.cbStatus.setText("Active");
        } else {
            this.cbStatus.setText("Deactive");
        }
    }
    @FXML
    private void hlChangePasswordOnAction(ActionEvent event) {
    }
    @FXML
    private void hlViewPermissionOnAction(ActionEvent event)
            throws IOException {
        this.usersGetway.selectedView(this.users);
        this.id = this.users.id;
        EmployeePermissionController pcc = new EmployeePermissionController();
        UserNameMedia usrID = new UserNameMedia();
        FXMLLoader loader = new FXMLLoader();
        System.out.println(this.id);
        URL resource = URLService.getFileAsResoure("application/employee/EmployeePermission.fxml");
        loader.setLocation(resource);
        loader.load();
        Parent root = (Parent) loader.getRoot();
        Scene scene = new Scene(root);
        scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
        EmployeePermissionController PermissionController = (EmployeePermissionController) loader.getController();
        this.nameMedia.setId(this.id);
        PermissionController.setMedia(this.nameMedia);
        PermissionController.checqPermission();
        Stage nStage = new Stage();
        nStage.setScene(scene);
        nStage.initModality(Modality.APPLICATION_MODAL);
        nStage.initStyle(StageStyle.TRANSPARENT);
        nStage.show();
    }
    @FXML
    private void hlViewUpdateHistory(ActionEvent event) throws IOException {
        String emp = "Employee";
        History history = new History();
        history.viewText(emp, this.tfUserName.getText(), this.name);
        System.out.println("view");
    }
    public void setselectedView() {
        clearAll();
        ListEmployee employeeList = (ListEmployee) this.tblEmoyeeList.getSelectionModel().getSelectedItem();
        if (employeeList != null) {
            this.users.id = employeeList.getEmployeeId();
            this.usersGetway.selectedView(this.users);
            this.id = this.users.id;
            this.tfUserName.setText(this.users.userName);
            this.tfFullName.setText(this.users.fullName);
            this.tfPhoneNumber.setText(this.users.contactNumber);
            this.tfEmailAddress.setText(this.users.emailAddress);
            this.tfSalary.setText(this.users.salary);
            this.tfDateofJoin.setText(this.users.date);
            this.creatorId = this.users.creatorId;
            this.taAddress.setText(this.users.address);
            this.image = this.users.image;
            this.recUsrImage.setFill(new ImagePattern(this.image));
            this.sql.creatorNameFindar(this.creatorId, this.lblCreator);
            this.tfCreatedBy.setText(this.lblCreator.getText());
            if (this.users.status.matches("1")) {
                this.cbStatus.setSelected(true);
                this.cbStatus.setText("Active");
            } else if (this.users.status.matches("0")) {
                this.cbStatus.setSelected(false);
                this.cbStatus.setText("Deactive");
            }
            if (this.users.id.matches("1")) {
                this.btnUpdate.setVisible(false);
                this.btnDelete.setVisible(false);
                this.hlChangePassword.setVisible(false);
                this.hlViewPermission.setVisible(false);
            } else {
                this.btnUpdate.setVisible(true);
                this.btnDelete.setVisible(true);
                this.hlChangePassword.setVisible(true);
                this.hlViewPermission.setVisible(true);
            }
        }
    }
    public void showDetails() {
        this.tblEmoyeeList.setItems(this.users.employeeLists);
        this.clmEmployeId.setCellValueFactory(new PropertyValueFactory("employeeId"));
        this.clmEmployeName.setCellValueFactory(new PropertyValueFactory("employeeName"));
        this.usersGetway.view(this.users);
    }
    public void checqPermission() {
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where UserId=?");
            this.pst.setString(1, this.userId);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt(13) != 1) {
                    this.hlChangePassword.setDisable(true);
                } else {
                    this.hlChangePassword.setDisable(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewEmployeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearAll() {
        this.tfUserName.clear();
        this.tfFullName.clear();
        this.tfCreatedBy.clear();
        this.tfSalary.clear();
        this.tfEmailAddress.clear();
        this.tfDateofJoin.clear();
        this.tfPhoneNumber.clear();
        this.taAddress.clear();
    }
}
