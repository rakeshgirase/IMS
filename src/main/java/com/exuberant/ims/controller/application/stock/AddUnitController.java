package com.exuberant.ims.controller.application.stock;

import com.exuberant.ims.bll.UnitBLL;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.getway.UnitGetway;
import com.exuberant.ims.media.UserNameMedia;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUnitController
        implements Initializable {
    public String unitId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    @FXML
    public Label lblContent;
    Unit unit = Unit.GRAMS;
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();
    CustomTf ctf = new CustomTf();
    @FXML
    private TextField tfUnitName;
    @FXML
    private Button btnClrUnitName;
    private Long usrId;
    private UserNameMedia nameMedia;
    @FXML
    private TextArea taDescription;
    @FXML
    private Button btnClose;

    public UserNameMedia getNameMedia() {
        return this.nameMedia;
    }

    public void setNameMedia(UserNameMedia nameMedia) {
        this.usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.ctf.clearTextFieldByButton(this.tfUnitName, this.btnClrUnitName);
        BooleanBinding bb = this.tfUnitName.textProperty().isEmpty();
        this.btnSave.disableProperty().bind(bb);
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }

    public void showDetails() {
    }
}
