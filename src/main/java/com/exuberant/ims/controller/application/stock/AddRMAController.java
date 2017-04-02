package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.bll.RmaBLL;
import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.getway.RmaGetway;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import com.exuberant.ims.media.UserNameMedia;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
public class AddRMAController
        implements Initializable {
    @FXML
    public Label lblContent;
    public String rmaId;
    @FXML
    public Button btnSave;
    @FXML
    public Button btnUpdate;
    CustomTf ctf = new CustomTf();
    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private UserNameMedia media;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfRMAName;
    @FXML
    private TextField tfRMADayes;
    @FXML
    private TextArea taRMAComment;
    public UserNameMedia getMedia() {
        return this.media;
    }
    public void setMedia(UserNameMedia media) {
        this.usrId = media.getId();
        this.media = media;
    }
    public void initialize(URL url, ResourceBundle rb) {
        BooleanBinding binding = this.tfRMAName.textProperty().isEmpty().or(this.tfRMADayes.textProperty().isEmpty());
        this.btnSave.disableProperty().bind(binding);
        this.ctf.numaricTextfield(this.tfRMADayes);
    }
    @FXML
    private void btnSave(ActionEvent event) {
        this.rma.id = this.rmaId;
        this.rma.rmaName = this.tfRMAName.getText().trim();
        this.rma.rmaDays = this.tfRMADayes.getText().trim();
        this.rma.rmaComment = this.taRMAComment.getText();
        this.rma.creatorId = this.usrId;
        this.rmaBLL.save(this.rma);
    }
    @FXML
    private void tfRMADays(KeyEvent event) {
        if (!this.tfRMADayes.getText().matches("[0-9]*")) {
            this.tfRMADayes.clear();
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        this.rma.id = this.rmaId;
        this.rma.rmaName = this.tfRMAName.getText().trim();
        this.rma.rmaDays = this.tfRMADayes.getText().trim();
        this.rma.rmaComment = this.taRMAComment.getText();
        this.rmaBLL.update(this.rma);
    }
    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) this.btnClose.getScene().getWindow();
        stage.close();
    }
    public void showDetails() {
        this.rma.id = this.rmaId;
        this.rmaGetway.selectedView(this.rma);
        this.tfRMAName.setText(this.rma.rmaName);
        this.tfRMADayes.setText(this.rma.rmaDays);
        this.taRMAComment.setText(this.rma.rmaComment);
    }
}
