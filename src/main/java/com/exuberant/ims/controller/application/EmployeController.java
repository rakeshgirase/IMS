package com.exuberant.ims.controller.application;

import com.exuberant.ims.controller.application.employee.AddEmployeController;
import com.exuberant.ims.controller.application.employee.ViewEmployeController;
import com.exuberant.ims.media.UserNameMedia;
import com.exuberant.ims.storekeeper.URLService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeController
        implements Initializable {
    @FXML
    public BorderPane bpContent;
    Image image = new Image(URLService.getFileAsStream("icon/d.png"));
    @FXML
    private MenuItem btnViewEmployee;
    @FXML
    private MenuItem btnAddEmployee;
    private Long userId;
    private UserNameMedia nameMedia;
    @FXML
    private StackPane spEmployeContent;
    @FXML
    private Label lblView;
    @FXML
    private ImageView ivEmployeIcon;

    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }

    public void setNameMedia(UserNameMedia nameMedia) {
        this.userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.ivEmployeIcon.setImage(this.image);
    }

    @FXML
    public void btnViewEmployeeOnAction(ActionEvent event) throws IOException {
        this.lblView.setText("Employee");
        ViewEmployeController vec = new ViewEmployeController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/employee/ViewEmploye.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        ViewEmployeController viewEmployeController = (ViewEmployeController) fXMLLoader.getController();
        viewEmployeController.setNameMedia(this.nameMedia);
        viewEmployeController.showDetails();
        viewEmployeController.btnClrCreatortf.getStylesheets().add("view/style/btnOnText.css");
        viewEmployeController.btnClrEmailtf.getStylesheets().add("view/style/btnOnText.css");
        viewEmployeController.btnClrFulNametf.getStylesheets().add("view/style/btnOnText.css");
        viewEmployeController.btnClrSalarytf.getStylesheets().add("view/style/btnOnText.css");
        viewEmployeController.btnClrPhonetf.getStylesheets().add("view/style/btnOnText.css");
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spEmployeContent.getChildren().clear();
        this.spEmployeContent.getChildren().add(acPane);
    }

    @FXML
    private void btnAddEmployeeOnClick(ActionEvent event) throws IOException {
        this.lblView.setText("Add Employee");
        AddEmployeController vec = new AddEmployeController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        URL resource = URLService.getFileAsResoure("application/employee/AddEmployee.fxml");
        fXMLLoader.load(resource.openStream());
        media.setId(this.userId);
        AddEmployeController addEmployeController = (AddEmployeController) fXMLLoader.getController();
        addEmployeController.setNameMedia(this.nameMedia);
        addEmployeController.btnClrEmail.getStylesheets().add("view/style/btnOnText.css");
        addEmployeController.btnClrFullName.getStylesheets().add("view/style/btnOnText.css");
        addEmployeController.btnClrPassword.getStylesheets().add("view/style/btnOnText.css");
        addEmployeController.btnClrPhone.getStylesheets().add("view/style/btnOnText.css");
        addEmployeController.btnClrSalary.getStylesheets().add("view/style/btnOnText.css");
        addEmployeController.btnClrUsrName.getStylesheets().add("view/style/btnOnText.css");
        AnchorPane acPane = (AnchorPane) fXMLLoader.getRoot();
        this.spEmployeContent.getChildren().clear();
        this.spEmployeContent.getChildren().add(acPane);
    }
}
