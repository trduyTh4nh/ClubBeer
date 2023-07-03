package com.example.project_ver1;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        DBHelper dbHelper = new DBHelper();
//        dbHelper.insertUser(new User(1,"Thanh", 20, "duythanh@gmail.com", "1234",  "0936936584",1));
//        dbHelper.insertUser(new User(2,"Quang", 20, "duythanh@gmail.com", "1234",  "0936936584",1));
        dbHelper.insertUser(new User(3,"Phát", 20, "duythanh@gmail.com", "1234",  "0936936584",1));

        ListView lv = (ListView) scene.lookup("#lvUsser");
        ResultSet rs = dbHelper.getUser();
        ArrayList<String> arrayList = new ArrayList<>();
//            ObservableList<User> users = new Ob
        while(rs.next()){
            arrayList.add((new User(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7))).toString());
        }
        ObservableList<String> arr = FXCollections.observableArrayList(arrayList);
        lv.setItems(arr);



    }

    public static void main(String[] args) {
        launch();
    }
}