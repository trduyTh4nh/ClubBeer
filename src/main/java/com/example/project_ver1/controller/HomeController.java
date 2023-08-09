package com.example.project_ver1.controller;

import com.example.project_ver1.HomeApplication;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private ImageView img_exit;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img_exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
    @FXML
    protected void OrderDetail() throws IOException{
        HomeApplication.changeStage("order-view.fxml");
    }
    @FXML
    protected void product() throws IOException {
        HomeApplication.changeStage("product-view.fxml");
    }
    @FXML
    protected void order() throws IOException{
        HomeApplication.changeStage("statistical-view.fxml");
    }
    @FXML
    protected void employ() throws IOException{
        HomeApplication.changeStage("employ-view.fxml");
    }
    @FXML
    protected void logout() throws IOException{
        HomeApplication.changeStage("login-view.fxml");
    }

    @FXML
    protected void orderAction() throws IOException{
        HomeApplication.changeStage("order-view.fxml");
    }


}