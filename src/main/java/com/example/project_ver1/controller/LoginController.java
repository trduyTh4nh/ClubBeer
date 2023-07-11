package com.example.project_ver1.controller;

import com.example.project_ver1.HomeApplication;
import com.example.project_ver1.model.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private ImageView img_login;
    @FXML
    private TextField text_login;
    @FXML
    private TextField text_pass;



    public LoginController() throws SQLException, IOException {
        FXMLLoader login = new FXMLLoader(HomeApplication.class.getResource("login-view.fxml"));
//        DB db = new DB();
//        String email = this.text_login.getText().toString();
//        String password = this.text_pass.getText().toString();
//        Alert alertInFo = new Alert(Alert.AlertType.CONFIRMATION);
//        alertInFo.setTitle("Thông báo");
//        if(db.login(email, password)){
//            alertInFo.setContentText("Đăng nhập thành công!");
//            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
//            Scene sceneMain = new Scene(fxmlLoader.load());
//
//        }
//        else {
//            alertInFo.setContentText("Đăng nhập thất bại");
//        }

    }

}
