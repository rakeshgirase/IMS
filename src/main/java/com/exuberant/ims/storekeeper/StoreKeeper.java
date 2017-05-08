package com.exuberant.ims.storekeeper;

import com.exuberant.ims.gateway.ProductGateway;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoreKeeper extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        try {
            URL resource = URLService.getFileAsResoure("Login.fxml");
            Parent root = (Parent) FXMLLoader.load(resource);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to Urvi Masale -Login");
            //           primaryStage.getIcons().add(new Image("/com/exuberant/ims/image/icon.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(500.0D);
            primaryStage.setMinWidth(850.0D);
            primaryStage.show();
            //FIXME: Find Better way to load Hibernate beforehand
            new ProductGateway().getAll();
        } catch (IOException ex) {
            Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}