package com.exuberant.ims.controller.application.stock;
import com.exuberant.ims.bll.UnitBLL;
import com.exuberant.ims.dal.Unit;
import com.exuberant.ims.getway.UnitGetway;
import com.exuberant.ims.list.ListUnit;
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
public class ViewUnitController
        implements Initializable {
    Unit unit = new Unit();
    UnitGetway unitGetway = new UnitGetway();
    UnitBLL unitBLL = new UnitBLL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String usrId;
    private String creatorId;
    private String unitId;
    private UserNameMedia media;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<ListUnit> tblViewUnit;
    @FXML
    private TableColumn<Object, Object> clmUnitId;
    @FXML
    private TableColumn<Object, Object> clmUnitName;
    @FXML
    private TableColumn<Object, Object> clmUnitDescription;
    @FXML
    private TableColumn<Object, Object> clmUnitCreator;
    @FXML
    private TableColumn<Object, Object> clmUnitCreateDate;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private MenuItem miSearch;
    @FXML
    private MenuItem miAddNew;
    @FXML
    private MenuItem miUpdate;
    @FXML
    private MenuItem miDelete;
    @FXML
    private MenuItem miView;
    @FXML
    private Button btnRefresh;
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
    private void tblViewUnitOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            viewDetails();
        } else {
            System.out.println(event.getClickCount());
        }
    }
    @FXML
    private void btnAddItemOnAction(ActionEvent event) {
        AddUnitController addUnitController = new AddUnitController();
        UserNameMedia media = new UserNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddUnit.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = (Parent) fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
            AddUnitController unitController = (AddUnitController) fxmlLoader.getController();
            media.setId(this.usrId);
            unitController.setNameMedia(media);
            unitController.lblContent.setText("ADD UNIT");
            unitController.btnUpdate.setVisible(false);
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
    private void btnUpdateOnAction(ActionEvent event) {
        if (this.tblViewUnit.getSelectionModel().getSelectedItem() != null) {
            viewDetails();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }
    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        if (!this.tblViewUnit.getSelectionModel().isEmpty()) {
            ListUnit selectedUnit = (ListUnit) this.tblViewUnit.getSelectionModel().getSelectedItem();
            String unitName = selectedUnit.getUnitName();
            this.unitId = selectedUnit.getUnitId();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Login Now");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                this.unit.id = this.unitId;
                this.unitBLL.delete(this.unit);
                tfSearchOnKeyResele(event);
            }
        } else {
            System.out.println("NULL SELECTED");
        }
    }
    @FXML
    private void miSearchOnAction(ActionEvent event) {
        this.tfSearch.requestFocus();
    }
    @FXML
    private void miAddNewOnAction(ActionEvent event) {
        btnAddItemOnAction(event);
    }
    @FXML
    private void miUpdateOnAction(ActionEvent event) {
        btnUpdateOnAction(event);
    }
    @FXML
    private void miDeleteOnAction(ActionEvent event) {
        btnDeleteOnAction(event);
    }
    @FXML
    private void miViewOnAction(ActionEvent event) {
        miUpdateOnAction(event);
    }
    public void showDetails() {
        this.tblViewUnit.setItems(this.unit.unitDetails);
        this.clmUnitId.setCellValueFactory(new PropertyValueFactory("unitId"));
        this.clmUnitName.setCellValueFactory(new PropertyValueFactory("unitName"));
        this.clmUnitDescription.setCellValueFactory(new PropertyValueFactory("unitDescription"));
        this.clmUnitCreator.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmUnitCreateDate.setCellValueFactory(new PropertyValueFactory("dateOfCreation"));
        this.unitGetway.view(this.unit);
    }
    private void viewDetails() {
        if (!this.tblViewUnit.getSelectionModel().isEmpty()) {
            ListUnit selectedUnit = (ListUnit) this.tblViewUnit.getSelectionModel().getSelectedItem();
            System.out.println("ID is");
            System.out.println(selectedUnit.getUnitId());
            String items = selectedUnit.getUnitId();
            if (!items.equals(null)) {
                AddUnitController addUnitController = new AddUnitController();
                UserNameMedia media = new UserNameMedia();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddUnit.fxml"));
                try {
                    fxmlLoader.load();
                    Parent parent = (Parent) fxmlLoader.getRoot();
                    Scene scene = new Scene(parent);
                    scene.setFill(new Color(0.0D, 0.0D, 0.0D, 0.0D));
                    AddUnitController unitController = (AddUnitController) fxmlLoader.getController();
                    media.setId(this.usrId);
                    unitController.setNameMedia(media);
                    unitController.lblContent.setText("UNIT DETAILS");
                    unitController.btnUpdate.setVisible(true);
                    unitController.btnSave.setVisible(true);
                    unitController.unitId = selectedUnit.getUnitId();
                    unitController.showDetails();
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
    public void tfSearchOnKeyResele(Event event) {
        this.unit.unitDetails.clear();
        this.unit.unitName = this.tfSearch.getText().trim();
        this.tblViewUnit.setItems(this.unit.unitDetails);
        this.clmUnitId.setCellValueFactory(new PropertyValueFactory("unitId"));
        this.clmUnitName.setCellValueFactory(new PropertyValueFactory("unitName"));
        this.clmUnitDescription.setCellValueFactory(new PropertyValueFactory("unitDescription"));
        this.clmUnitCreator.setCellValueFactory(new PropertyValueFactory("creatorName"));
        this.clmUnitCreateDate.setCellValueFactory(new PropertyValueFactory("dateOfCreation"));
        this.unitGetway.searchView(this.unit);
    }
    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        this.unit.unitDetails.clear();
        showDetails();
    }
}
/* Location:              C:\Users\INTEL\Downloads\com.exuberant.ims.storekeeper-alpha\com.exuberant.ims.storekeeper-alpha.jar!\com.exuberant.ims.controller\application\stock\ViewUnitController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */