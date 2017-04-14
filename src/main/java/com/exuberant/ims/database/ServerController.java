package com.exuberant.ims.database;

import com.exuberant.ims.util.PropertyService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController
        implements Initializable {
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;
    Connection con;
    String url;
    String user;
    String pass;
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    @FXML
    private TextField tfHost;
    @FXML
    private TextField thPort;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnConnect;
    @FXML
    private Button btnReset;
    @FXML
    private Label lablServerStatus;
    @FXML
    private TextField tfDBName;
    @FXML
    private TextField tfUserName;

    public void initialize(URL url, ResourceBundle rb) {
        getDataFromFile();
    }

    @FXML
    private void btnConnectOnAction(ActionEvent event) {
        mkDbProperties();
    }

    @FXML
    private void btnResetOnAction(ActionEvent event) {
    }

    public void getDataFromFile() {
        this.tfHost.setText(PropertyService.getInstance().getProperty("host"));
        this.tfDBName.setText(PropertyService.getInstance().getProperty("db"));
        this.tfUserName.setText(PropertyService.getInstance().getProperty("user"));
        this.pfPassword.setText(PropertyService.getInstance().getProperty("password"));
        this.thPort.setText(PropertyService.getInstance().getProperty("port"));
    }

    public void mkDbProperties() {
        try {
            this.properties.setProperty("host", this.tfHost.getText().trim());
            this.properties.setProperty("port", this.thPort.getText().trim());
            this.properties.setProperty("db", this.tfDBName.getText().trim());
            this.properties.setProperty("user", this.tfUserName.getText().trim());
            this.properties.setProperty("password", this.pfPassword.getText().trim());
            if (dbConnect()) {
                System.out.println("Connected to database...");
                this.con.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Server connect successfully");
                alert.setHeaderText("Login now");
                alert.setContentText("Server has been connected sucessfully \n to login now click ok");
                alert.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = alert.showAndWait();
                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    Stage stage = (Stage) this.thPort.getScene().getWindow();
                    stage.close();
                }
            } else {
                Alert error_alert = new Alert(AlertType.ERROR);
                error_alert.setTitle("Can't connect with mysql");
                error_alert.setHeaderText("Can't connect to mysql server");
                error_alert.setContentText("Try again");
                error_alert.initStyle(StageStyle.UNDECORATED);
                error_alert.show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadPropertiesFile() {
        this.url = PropertyService.getInstance().getProperty("url");
        this.user = this.properties.getProperty("user");
        this.pass = this.properties.getProperty("password");
    }

    private boolean dbConnect() {
        loadPropertiesFile();
        try {
            Class.forName(PropertyService.getInstance().getProperty("driverName"));
            //this.con = DriverManager.getConnection(this.url + this.unicode, this.users, this.password);
            url = PropertyService.getInstance().getProperty("url");
            System.err.println("this.url: " + url);
            this.con = DriverManager.getConnection(this.url);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Too Many Connection");
        }
        return false;
    }
}