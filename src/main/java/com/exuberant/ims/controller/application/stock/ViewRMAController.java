package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.bll.RmaBLL;
import com.exuberant.ims.dal.RMA;
import com.exuberant.ims.getway.RmaGetway;
import com.exuberant.ims.list.ListRma;
import com.exuberant.ims.database.DBConnection;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.exuberant.ims.media.UserNameMedia;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
public class ViewRMAController
        implements Initializable {
    RMA rma = new RMA();
    RmaGetway rmaGetway = new RmaGetway();
    RmaBLL rmaBLL = new RmaBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private String rmaId;
    private UserNameMedia media;
    private String creatorId;
    @FXML
    private TableView<ListRma> tblViewRMA;
    @FXML
    private TableColumn<Object, Object> clmRMAId;
    @FXML
    private TableColumn<Object, Object> clmRMAName;
    @FXML
    private Button btnAddNew;
    @FXML
    private TableColumn<Object, Object> clmRMADayes;
    @FXML
    private TableColumn<Object, Object> clmRMADiscription;
    @FXML
    private TableColumn<Object, Object> clmRMACreator;
    @FXML
    private TableColumn<Object, Object> clmRMADate;
    @FXML
    private Button btnRefresh;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    public UserNameMedia getMedia() {
        return this.media;
    }
    public void setMedia(UserNameMedia media) {
        this.usrId = media.getId();
        this.media = media;
    }
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void tblViewRMAOnClick(MouseEvent event) {
        if ((!this.tblViewRMA.getSelectionModel().isEmpty()) &&
                (event.getClickCount() == 2)) {
            viewDetails();
        }
    }
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!this.tblViewRMA.getSelectionModel().isEmpty()) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!this.tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRMA = (ListRma) this.tblViewRMA.getSelectionModel().getSelectedItem();
            String rmaName = selectedRMA.getRmaName();
            this.rmaId = selectedRMA.getRamId();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                this.rma.id = this.rmaId;
                this.rmaBLL.delete(this.rma);
                tfSearchOnKeyRelesh(event);
            }
        } else {
            System.out.println("NULL SELECTED");
        }
    }
    public void showDetails() {
        this.tblViewRMA.setItems(this.rma.rmaDetails);
        this.clmRMAId.setCellValueFactory(new PropertyValueFactory("ramId"));
        this.clmRMAName.setCellValueFactory(new PropertyValueFactory("rmaName"));
        this.clmRMADayes.setCellValueFactory(new PropertyValueFactory("rmaDays"));
        this.clmRMADiscription.setCellValueFactory(new PropertyValueFactory("rmaComment"));
        this.clmRMACreator.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmRMADate.setCellValueFactory(new PropertyValueFactory("date"));
        this.rmaGetway.view(this.rma);
    }
    @FXML
    private void tblViewRMAOnKeyResele(KeyEvent event) {
    }
    @FXML
    public void btnAddNew(ActionEvent actionEvent) {
        AddRMAController addRMAController = new AddRMAController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddRMAController rmaController = (AddRMAController) fxmlLoader.getController();
            media.setId(this.usrId);
            rmaController.setMedia(media);
            rmaController.lblContent.setText("ADD RMA");
            rmaController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void tfSearchOnKeyRelesh(Event event) {
        this.rma.rmaDetails.clear();
        this.rma.rmaName = this.tfSearch.getText().trim();
        this.tblViewRMA.setItems(this.rma.rmaDetails);
        this.clmRMAId.setCellValueFactory(new PropertyValueFactory("ramId"));
        this.clmRMAName.setCellValueFactory(new PropertyValueFactory("rmaName"));
        this.clmRMADayes.setCellValueFactory(new PropertyValueFactory("rmaDays"));
        this.clmRMADiscription.setCellValueFactory(new PropertyValueFactory("rmaComment"));
        this.clmRMACreator.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmRMADate.setCellValueFactory(new PropertyValueFactory("date"));
        this.rmaGetway.searchView(this.rma);
    }
    private void viewDetails() {
        if (!this.tblViewRMA.getSelectionModel().isEmpty()) {
            ListRma selectedRma = (ListRma) this.tblViewRMA.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedRma.getRamId());
            String items = selectedRma.getRamId();
            if (!items.equals(null)) {
                AddUnitController addUnitController = new AddUnitController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddRMA.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = (Parent) fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    AddRMAController rmaController = (AddRMAController) fxmlLoader.getController();
                    media.setId(this.usrId);
                    rmaController.setMedia(media);
                    rmaController.lblContent.setText("RMA DETAILS");
                    rmaController.btnUpdate.setVisible(true);
                    rmaController.btnSave.setVisible(true);
                    rmaController.rmaId = selectedRma.getRamId();
                    rmaController.showDetails();
                    Stage nStage = new Stage();
                    nStage.setScene(scene);
                    nStage.initModality(Modality.APPLICATION_MODAL);
                    nStage.initStyle(StageStyle.TRANSPARENT);
                    nStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("empty Selection");
        }
    }
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.rma.rmaDetails.clear();
        showDetails();
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\stock\ViewRMAController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */