package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.bll.UnitBLL;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.getway.UnitGetway;
import com.exuberant.ims.custom.CustomTf;
import com.exuberant.ims.database.DBConnection;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    Unit unit = new Unit();
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();
    CustomTf ctf = new CustomTf();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private TextField tfUnitName;
    @FXML
    private Button btnClrUnitName;
    private String usrId;
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
        this.unit.unitName = this.tfUnitName.getText().trim();
        this.unit.unitDescription = this.taDescription.getText().trim();
        this.unit.creatorId = this.usrId;
        this.unitBLL.save(this.unit);
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        this.unit.id = this.unitId;
        this.unit.unitName = this.tfUnitName.getText().trim();
        this.unit.unitDescription = this.taDescription.getText().trim();
        this.unitGetway.update(this.unit);
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    public void showDetails() {
        this.unit.id = this.unitId;
        this.unitGetway.selectedView(this.unit);
        this.tfUnitName.setText(this.unit.unitName);
        this.taDescription.setText(this.unit.unitDescription);
    }
}
