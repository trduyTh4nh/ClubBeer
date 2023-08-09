package com.example.project_ver1.controller;

import com.example.project_ver1.HomeApplication;
import com.example.project_ver1.class_model.Role;
import com.example.project_ver1.model.DB;
import com.example.project_ver1.model.LoginDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private ImageView img_login;
    @FXML
    private TextField text_login;
    @FXML
    private PasswordField text_pass;
    @FXML
    private Button btn_login;

    private LoginDetails instance = LoginDetails.INSTANCE;
    DB db = new DB();
    public LoginController() throws SQLException, IOException {



    }
    @FXML
    public void login() throws SQLException, IOException {
        String email = text_login.getText().toString();
        String password = text_pass.getText().toString();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        if(db.loginFunc(email, password)){
            alert.setContentText("Đăng nhập thành công");
            alert.show();
            //Parent p = (Parent) root.load();
            instance.setEmail(email);
            instance.setPassword(password);
            Role r = db.getRoleByEmail(email);
            if(r.getId().equals("nhanv")){
                HomeApplication.changeStage("sell-view.fxml");
            } else
                HomeApplication.changeStage("home-view.fxml");
        }
        else {
            alert.setContentText("Đăng nhập thất bại");
            alert.show();
        }

    }
}
