package com.example.project_ver1.controller;

import com.example.project_ver1.model.DB;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private ImageView img_login;
    @FXML
    private TextField text_login;
    @FXML
    private TextField text_pass;




    public LoginController() throws SQLException {
        DB db = new DB();

        db.login("duythanh@gmail.com", "1234");

    }
}
