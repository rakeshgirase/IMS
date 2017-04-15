package com.exuberant.ims.controller;

import com.exuberant.ims.controller.application.EmployeController;
import com.exuberant.ims.controller.application.SellController;
import com.exuberant.ims.controller.application.SettingsController;
import com.exuberant.ims.controller.application.StockController;
import com.exuberant.ims.controller.application.stock.AddProductController;
import com.exuberant.ims.dal.Users;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationController implements Initializable {
    String usrName;
    Long id;

    Users users = new Users();
    Image menuImage = new Image(URLService.getFileAsStream("icon/menu.png"));
    Image menuImageRed = new Image(URLService.getFileAsStream("icon/menuRed.png"));
    Image image;
    String defultStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:none";
    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;-fx-border-color:#FF4E3C";
    Image home = new Image(URLService.getFileAsStream("icon/home.png"));
    Image homeRed = new Image(URLService.getFileAsStream("icon/homeRed.png"));
    Image stock = new Image(URLService.getFileAsStream("icon/stock.png"));
    Image stockRed = new Image(URLService.getFileAsStream("icon/stockRed.png"));
    Image sell = new Image(URLService.getFileAsStream("icon/sell2.png"));
    Image sellRed = new Image(URLService.getFileAsStream("icon/sell2Red.png"));
    Image employee = new Image(URLService.getFileAsStream("icon/employee.png"));
    Image employeeRed = new Image(URLService.getFileAsStream("icon/employeeRed.png"));
    Image setting = new Image(URLService.getFileAsStream("icon/settings.png"));
    Image settingRed = new Image(URLService.getFileAsStream("icon/settingsRed.png"));
    Image about = new Image(URLService.getFileAsStream("icon/about.png"));
    Image aboutRed = new Image(URLService.getFileAsStream("icon/aboutRed.png"));
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
    private ImageView imgCurrentStock;
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
        this.lblUserId.setText(String.valueOf(usrNameMedia.getId()));
        this.lblUsrName.setText(usrNameMedia.getUsers().getUserName());
        this.id = usrNameMedia.getId();
        this.usrName = usrNameMedia.getUsers().getUserName();
        this.usrNameMedia = usrNameMedia;
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.imgMenuBtn.setImage(this.menuImage);
        Image usrImg = new Image(URLService.getFileAsStream("image/dummy.jpg"));
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
        URL resource = URLService.getFileAsResoure("application/stock/AddProduct.fxml");
        fXMLLoader.load(resource.openStream());
        nm.setId(this.id);
        AddProductController addProductControllerr = fXMLLoader.getController();
        /*addProductControllerr.bpStore.getStylesheets().add("view.style/MainStyle.css");
        addProductControllerr.sesetUserId(this.usrNameMedia);
        addProductControllerr.btnStockOnAction(event);
        addProductControllerr.settingPermission();*/
        //AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.acContent.getChildren().clear();
        //this.acContent.getChildren().add(acPane);
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
        employeController.bpContent.getStylesheets().add("view/style/MainStyle.css");
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
        settingControl.bpSettings.getStylesheets().add("view/style/MainStyle.css");
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
            sellController.acMainSells.getStylesheets().add("view/style/MainStyle.css");
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

    }

    private void homeActive() {
        this.imgHomeBtn.setImage(this.homeRed);
        this.imgCurrentStock.setImage(this.home);
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

    private void currentStockActive() {
        this.imgHomeBtn.setImage(this.home);
        this.imgCurrentStock.setImage(this.homeRed);
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
        this.imgCurrentStock.setImage(this.home);
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
        this.imgCurrentStock.setImage(this.home);
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
        this.imgCurrentStock.setImage(this.home);
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
        this.imgCurrentStock.setImage(this.home);
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
        this.imgCurrentStock.setImage(this.home);
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
        this.users.setId(this.id);
        //this.image = this.users.image;
        //this.circleImgUsr.setFill(new ImagePattern(this.image));
        //this.imgUsrTop.setFill(new ImagePattern(this.image));
        this.lblFullName.setText(this.users.getFullName());
        this.lblUsrNamePopOver.setText(this.users.getUserName());
    }

    public void btnCurrentStockOnClick(ActionEvent actionEvent) throws IOException {
        currentStockActive();
        this.acContent.getChildren().clear();

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            URL resource = URLService.getFileAsResoure("application/stock/AddCurrentStock.fxml");
            fxmlLoader.load(resource.openStream());
        } catch (IOException localIOException) {
        }
        //this.acContent.getChildren().add(FXMLLoader.load(resource));
        AnchorPane root = (AnchorPane) fxmlLoader.getRoot();
        this.acContent.getChildren().clear();
        this.acContent.getChildren().add(root);
    }
}
