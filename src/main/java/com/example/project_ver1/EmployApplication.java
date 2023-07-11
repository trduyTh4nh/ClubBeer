package com.example.project_ver1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EmployApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader employView = new FXMLLoader(HomeApplication.class.getResource("employ-view.fxml"));
        Scene sceneEmploy = new Scene(employView.load());
        stage.setScene(sceneEmploy);
        stage.show();

    }
}
