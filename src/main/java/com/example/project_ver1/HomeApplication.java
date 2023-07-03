package com.example.project_ver1;

import com.example.project_ver1.model.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class HomeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        FXMLLoader login = new FXMLLoader(HomeApplication.class.getResource("login-view.fxml"));
        Scene loginScene = new Scene(login.load());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Club beer!");
        stage.setScene(loginScene);
        stage.show();

        DB dbHelper = new DB();
//        dbHelper.insertUser(new User(1,"Thanh", 20, "duythanh@gmail.com", "1234",  "0936936584",1));
//        dbHelper.insertUser(new User(2,"Quang", 20, "duythanh@gmail.com", "1234",  "0936936584",1));
     //   dbHelper.insertUser(new User(3,"Phát", 20, "duythanh@gmail.com", "1234",  "0936936584",1));
        ImageView iv = (ImageView) loginScene.lookup("#img_login");
        InputStream stream = new FileInputStream("C:\\Users\\ASUS\\Downloads\\club-beer-logo.png");
        Image i = new Image(stream);
        iv.setImage(i);

    }

    public static void main(String[] args) {
        launch();
    }
}