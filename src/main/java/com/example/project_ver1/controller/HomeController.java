package com.example.project_ver1.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HomeController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField height;
    @FXML
    private ListView lvUsser;
    public void Submit (ActionEvent event){




    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}