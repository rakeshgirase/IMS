package com.exuberant.ims.storekeeper;

import com.exuberant.ims.database.DBModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreKeeper extends Application {

    public StoreKeeper() {
        DBModel model = new DBModel();
        model.createDataBase();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = (Parent) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to com.exuberant.ims.storekeeper -Login");
            primaryStage.getIcons().add(new Image("/com/exuberant/ims/image/icon.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(500.0D);
            primaryStage.setMinWidth(850.0D);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}