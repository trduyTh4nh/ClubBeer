package com.example.project_ver1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader orderView = new FXMLLoader(HomeApplication.class.getResource("order-view.fxml"));
        Scene sceneOrder = new Scene(orderView.load());
        stage.setScene(sceneOrder);
        stage.show();
    }
}
