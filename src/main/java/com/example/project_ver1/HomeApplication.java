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
        FXMLLoader cartView = new FXMLLoader(HomeApplication.class.getResource("cart-view.fxml"));
        FXMLLoader productView = new FXMLLoader(HomeApplication.class.getResource("product-view.fxml"));
        FXMLLoader employView = new FXMLLoader(HomeApplication.class.getResource("employ-view.fxml"));
        FXMLLoader orderView = new FXMLLoader(HomeApplication.class.getResource("order-view.fxml"));


        Scene loginScene = new Scene(login.load());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Club beer!");
        stage.setScene(scene);
        stage.show();

        DB dbHelper = new DB();


        ImageView iv = (ImageView) loginScene.lookup("#img_login");


        InputStream stream = new FileInputStream("C:\\Users\\ASUS\\Downloads\\club-beer-logo.png");
        Image i = new Image(stream);
        iv.setImage(i);

        ImageView home = (ImageView) scene.lookup("#img_home");
        InputStream stream1 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\home.png");
        Image i1 = new Image(stream1);
        home.setImage(i1);

        ImageView close = (ImageView) scene.lookup("#img_exit");
        InputStream stream2 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\close.png");
        Image i2 = new Image(stream2);
        close.setImage(i2);

        ImageView beer = (ImageView) scene.lookup("#img_beer");
        InputStream stream3 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\beer.png");
        Image i3 = new Image(stream3);
        beer.setImage(i3);

        ImageView cart = (ImageView) scene.lookup("#img_cart");
        InputStream stream4 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\online-shopping.png");
        Image i4 = new Image(stream4);
        cart.setImage(i4);

        ImageView employ = (ImageView) scene.lookup("#img_employ");
        InputStream stream5 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\team-management.png");
        Image i5 = new Image(stream5);
        employ.setImage(i5);

        ImageView order = (ImageView) scene.lookup("#img_order");
        InputStream stream6 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\shopping-list.png");
        Image i6 = new Image(stream6);
        order.setImage(i6);

        ImageView logout = (ImageView) scene.lookup("#img_logout");
        InputStream stream7 = new FileInputStream("D:\\HocTap\\LTJava\\ClubBeer\\src\\main\\java\\com\\example\\project_ver1\\images\\logout.png");
        Image i7 = new Image(stream7);
        logout.setImage(i7);



    }

    public static void main(String[] args) {
        launch();
    }
}