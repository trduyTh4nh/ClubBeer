package com.example.project_ver1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductApplication extends Application {
    Button back;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader productView = new FXMLLoader(ProductApplication.class.getResource("product-view.fxml"));

        Scene sceneProduct = new Scene(productView.load());
        stage.setScene(sceneProduct);
        stage.show();
        Button button = (Button) sceneProduct.lookup("#back");
        button.setOnAction(result -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("home-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.getScene().setRoot(root);
            stage.show();

        });

    }

    public static void main(String[] args) {
        launch();
    }
}
