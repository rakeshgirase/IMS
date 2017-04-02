package com.exuberant.ims.custom;
import com.exuberant.ims.controller.popup.SucessController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PopUp {
    public void sucessMessage() {
        SucessController sc = new SucessController();
        System.out.println("COmm");
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/popup/Sucess.fxml").openStream());
            fXMLLoader.load();
            SucessController sucessController = (SucessController) fXMLLoader.getController();
            System.out.println("Come 2");
            Parent parent = (Parent) fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.setResizable(false);
            nStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PopUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
