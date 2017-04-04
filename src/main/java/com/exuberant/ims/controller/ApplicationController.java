package com.exuberant.ims.controller;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.getway.UsersGetway;
import com.exuberant.ims.controller.application.EmployeController;
import com.exuberant.ims.controller.application.SellController;
import com.exuberant.ims.controller.application.SettingsController;
import com.exuberant.ims.controller.application.StockController;
import com.exuberant.ims.database.DBConnection;

import com.exuberant.ims.storekeeper.URLService;
import com.exuberant.ims.util.PropertyService;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
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
public class ApplicationController implements Initializable {
    String usrName;
    String id;
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    String db = PropertyService.getInstance().getProperty("db");
    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();
    Image menuImage = new Image("/com/exuberant/ims/icon/menu.png");
    Image menuImageRed = new Image("/com/exuberant/ims/icon/menuRed.png");
    Image image;
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:none";
    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:#FF4E3C";
    Image home = new Image("/com/exuberant/ims/icon/home.png");
    Image homeRed = new Image("/com/exuberant/ims/icon/homeRed.png");
    Image stock = new Image("/com/exuberant/ims/icon/stock.png");
    Image stockRed = new Image("/com/exuberant/ims/icon/stockRed.png");
    Image sell = new Image("/com/exuberant/ims/icon/sell2.png");
    Image sellRed = new Image("/com/exuberant/ims/icon/sell2Red.png");
    Image employee = new Image("/com/exuberant/ims/icon/employee.png");
    Image employeeRed = new Image("/com/exuberant/ims/icon/employeeRed.png");
    Image setting = new Image("/com/exuberant/ims/icon/settings.png");
    Image settingRed = new Image("/com/exuberant/ims/icon/settingsRed.png");
    Image about = new Image("/com/exuberant/ims/icon/about.png");
    Image aboutRed = new Image("/com/exuberant/ims/icon/aboutRed.png");
    @FXML
    private StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private Button btnStore;
    @FXML
    private ImageView imgStoreBtn;
    @FXML
    private Button btnEmplopye;
    @FXML
    private ImageView imgEmployeBtn;
    @FXML
    private Button btnSell;
    @FXML
    private ImageView imgSellBtn;
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private Label lblUsrName;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUserId;
    private UserNameMedia usrNameMedia;
    public UserNameMedia getUserNameMedia() {
        return this.usrNameMedia;
    }
    public void setUsrNameMedia(UserNameMedia usrNameMedia) {
        this.lblUserId.setText(usrNameMedia.getId());
        this.lblUsrName.setText(usrNameMedia.getUserName());
        this.id = usrNameMedia.getId();
        this.usrName = usrNameMedia.getUserName();
        this.usrNameMedia = usrNameMedia;
    }
    public void initialize(URL url, ResourceBundle rb) {
        this.imgMenuBtn.setImage(this.menuImage);
        Image usrImg = new Image("/com/exuberant/ims/image/rifat.jpg");
        this.imgUsrTop.setFill(new ImagePattern(usrImg));
        this.circleImgUsr.setFill(new ImagePattern(usrImg));
    }
    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (this.sideMenuToogleBtn.isSelected()) {
            this.imgMenuBtn.setImage(this.menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0D), this.acDashBord);
            sideMenu.setByX(-130.0D);
            sideMenu.play();
            this.acDashBord.getChildren().clear();
        } else {
            this.imgMenuBtn.setImage(this.menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0D), this.acDashBord);
            sideMenu.setByX(130.0D);
            sideMenu.play();
            this.acDashBord.getChildren().add(this.leftSideBarScroolPan);
        }
    }
    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        this.acContent.getChildren().clear();
        URL resource = URLService.getFileAsResoure("Login.fxml");
        this.acContent.getChildren().add(FXMLLoader.load(resource));
        this.acDashBord.getChildren().clear();
        this.acHead.getChildren().clear();
        this.acHead.setMaxHeight(0.0D);
    }
    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) this.acMain.getScene().getWindow();
            stage.setFullScreen(true);
        }
    }
    @FXML
    public void btnHomeOnClick(ActionEvent event) {
        homeActive();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            URL resource = URLService.getFileAsResoure("application/home/Home.fxml");
            fxmlLoader.load(resource.openStream());
        } catch (IOException localIOException) {
        }
        AnchorPane root = (AnchorPane) fxmlLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(root);
        System.out.println(this.lblUsrName.getText());
        System.out.println(this.lblUserId.getText());
    }
    @FXML
    private void btnStoreOnClick(ActionEvent event) throws IOException {
        sotreActive();
        StockController sc = new StockController();
        UserNameMedia nm = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/Stock.fxml");
        fXMLLoader.load(resource.openStream());
        nm.setId(this.id);
        StockController stockController = (StockController) fXMLLoader.getController();
        stockController.bpStore.getStylesheets().add("/style/MainStyle.css");
        stockController.setUserId(this.usrNameMedia);
        stockController.btnStockOnAction(event);
        stockController.settingPermission();
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }
    @FXML
    private void btnEmplopyeOnClick(ActionEvent event) throws IOException {
        employeeActive();
        EmployeController ec = new EmployeController();
        UserNameMedia nm = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/Employee.fxml");
        fXMLLoader.load(resource);
        nm.setId(this.id);
        EmployeController employeController = (EmployeController) fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/style/MainStyle.css");
        employeController.setNameMedia(this.usrNameMedia);
        employeController.btnViewEmployeeOnAction(event);
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }
    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {
        settingsActive();
        SettingsController settingController = new SettingsController();
        UserNameMedia usrMedia = new UserNameMedia();
        FXMLLoader settingLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/Settings.fxml");
        settingLoader.load(resource.openStream());
        usrMedia.setId(this.id);
        SettingsController settingControl = (SettingsController) settingLoader.getController();
        settingControl.bpSettings.getStylesheets().add("/style/MainStyle.css");
        settingControl.setUsrMedia(usrMedia);
        settingControl.miMyASccountOnClick(event);
        settingControl.settingPermission();
        AnchorPane acPane = (AnchorPane) settingLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(acPane);
    }
    @FXML
    private void btnAboutOnClick(ActionEvent event) {
        try {
            aboutActive();
            FXMLLoader fXMLLoader = new FXMLLoader();
            URL resource = URLService.getFileAsResoure("application/about/AboutMe.fxml");
            fXMLLoader.load(resource);
            AnchorPane anchorPane = (AnchorPane) fXMLLoader.getRoot();
            this.acContent.getChildren().clear();
            this.acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void btnSellOnClick(ActionEvent event) {
        sellActive();
        SellController controller = new SellController();
        UserNameMedia nm = new UserNameMedia();
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            URL resource = URLService.getFileAsResoure("application/Sell.fxml");
            fXMLLoader.load(resource.openStream());
            nm.setId(this.id);
            SellController sellController = (SellController) fXMLLoader.getController();
            sellController.setNameMedia(this.usrNameMedia);
            sellController.acMainSells.getStylesheets().add("/style/MainStyle.css");
            sellController.tbtnSellOnAction(event);
            AnchorPane anchorPane = (AnchorPane) fXMLLoader.getRoot();
            this.acContent.getChildren().clear();
            this.acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void hlUpdateAccount(ActionEvent event) {
    }
    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }
    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
    }
    public void permission() {
        this.con = this.dbCon.getConnection();
        try {
            this.pst = this.con.prepareStatement("select * from " + this.db + ".UserPermission where UserId=?");
            this.pst.setString(1, this.id);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                if (this.rs.getInt(17) == 0) {
                    this.btnEmplopye.setDisable(true);
                }
                if (this.rs.getInt(15) == 0) {
                    this.btnSell.setDisable(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void homeActive() {
        this.imgHomeBtn.setImage(this.homeRed);
        this.imgStoreBtn.setImage(this.stock);
        this.imgSellBtn.setImage(this.sell);
        this.imgEmployeBtn.setImage(this.employee);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.btnHome.setStyle(this.activeStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnEmplopye.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
    }
    private void sotreActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.stockRed);
        this.imgSellBtn.setImage(this.sell);
        this.imgEmployeBtn.setImage(this.employee);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.activeStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnEmplopye.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
    }
    private void sellActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.stock);
        this.imgSellBtn.setImage(this.sellRed);
        this.imgEmployeBtn.setImage(this.employee);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.activeStyle);
        this.btnEmplopye.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
    }
    private void employeeActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.stock);
        this.imgSellBtn.setImage(this.sell);
        this.imgEmployeBtn.setImage(this.employeeRed);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.about);
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnEmplopye.setStyle(this.activeStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.defultStyle);
    }
    private void settingsActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.stock);
        this.imgSellBtn.setImage(this.sell);
        this.imgEmployeBtn.setImage(this.employee);
        this.imgSettingsBtn.setImage(this.settingRed);
        this.imgAboutBtn.setImage(this.about);
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnEmplopye.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.activeStyle);
        this.btnAbout.setStyle(this.defultStyle);
    }
    private void aboutActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgStoreBtn.setImage(this.stock);
        this.imgSellBtn.setImage(this.sell);
        this.imgEmployeBtn.setImage(this.employee);
        this.imgSettingsBtn.setImage(this.setting);
        this.imgAboutBtn.setImage(this.aboutRed);
        this.btnHome.setStyle(this.defultStyle);
        this.btnStore.setStyle(this.defultStyle);
        this.btnSell.setStyle(this.defultStyle);
        this.btnEmplopye.setStyle(this.defultStyle);
        this.btnSettings.setStyle(this.defultStyle);
        this.btnAbout.setStyle(this.activeStyle);
    }
    public void viewDetails() {
        this.users.id = this.id;
        this.usersGetway.selectedView(this.users);
        this.image = this.users.image;
        this.circleImgUsr.setFill(new ImagePattern(this.image));
        this.imgUsrTop.setFill(new ImagePattern(this.image));
        this.lblFullName.setText(this.users.fullName);
        this.lblUsrNamePopOver.setText(this.users.userName);
    }
}
