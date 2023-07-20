package com.example.project_ver1;

import com.example.project_ver1.constant.PathQuang;
import com.example.project_ver1.constant.PathThanh;
import com.example.project_ver1.model.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Objects;


public class HomeApplication extends Application {
    private static Stage s;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        s = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        FXMLLoader login = new FXMLLoader(HomeApplication.class.getResource("login-view.fxml"));
        FXMLLoader productView = new FXMLLoader(HomeApplication.class.getResource("product-view.fxml"));
        FXMLLoader orderView = new FXMLLoader(HomeApplication.class.getResource("order-view.fxml"));
        FXMLLoader employView = new FXMLLoader(HomeApplication.class.getResource("employ-view.fxml"));

        Scene sceneLogin = new Scene(login.load());
        Scene sceneMain = new Scene(fxmlLoader.load());
        Scene sceneProduct = new Scene(productView.load());
        Scene sceneOrder = new Scene(orderView.load());
        Scene sceneEmploy = new Scene(employView.load());

        stage.setResizable(false);
        stage.setTitle("Club beer!");
        stage.setScene(sceneProduct);
        stage.show();

        DB dbHelper = new DB();


        ImageView iv = (ImageView) sceneLogin.lookup("#img_login");
        Image i = new Image(Objects.requireNonNull(getClass().getResource("club-beer-logo.png")).toExternalForm());
        iv.setImage(i);
//        Button btnDashboard = (Button) sceneMain.lookup("#id_dashboard");
//        btnDashboard.setOnAction(result -> {
//                stage.setScene(sceneMain);
//        });
//        Button btnProduct = (Button) sceneMain.lookup("#id_btnProduct");
//        btnProduct.setOnAction(result -> {
//            stage.setScene(sceneProduct);
//        });
        Button buttonback1 = (Button) sceneProduct.lookup("#back");
        buttonback1.setOnAction(result -> {
            stage.setScene(sceneMain);
        });

        Button btnEmploy = (Button) sceneMain.lookup("#id_btnEmploy");
        btnEmploy.setOnAction(result -> {
            stage.setScene(sceneEmploy);
        });
        Button buttonback2 = (Button) sceneEmploy.lookup("#back");
        buttonback2.setOnAction(result -> {
            stage.setScene(sceneMain);
        });
        Button btnOrder = (Button) sceneMain.lookup("#id_btnOrder");
        btnOrder.setOnAction(result -> {
            stage.setScene(sceneOrder);
        });
        Button buttonback3 = (Button) sceneOrder.lookup("#back");
        buttonback3.setOnAction(result -> {
            stage.setScene(sceneMain);
        });
        Button btnLogout = (Button) sceneMain.lookup("#id_btnLogout");

    }
    public static void setBackbutton(Scene scene, String res){
        Button buttonback1 = (Button) scene.lookup("#back");
        buttonback1.setOnAction(result -> {
            try {
                changeStage(res);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public static void setImages(Scene sceneMain) throws FileNotFoundException {


        ImageView home = (ImageView) sceneMain.lookup("#img_home");

        Image i1 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("home.png")).toExternalForm());
        home.setImage(i1);

        ImageView close = (ImageView) sceneMain.lookup("#img_exit");
        Image i2 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("close.png")).toExternalForm());
        close.setImage(i2);

        ImageView beer = (ImageView) sceneMain.lookup("#img_beer");
        Image i3 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("beer.png")).toExternalForm());
        beer.setImage(i3);


        ImageView employ = (ImageView) sceneMain.lookup("#img_employ");
        Image i5 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("team-management.png")).toExternalForm());
        employ.setImage(i5);

        ImageView order = (ImageView) sceneMain.lookup("#img_order");
        Image i6 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("shopping-list.png")).toExternalForm());
        order.setImage(i6);

        ImageView logout = (ImageView) sceneMain.lookup("#img_logout");
        Image i7 = new Image(Objects.requireNonNull(HomeApplication.class.getResource("logout.png")).toExternalForm());
        logout.setImage(i7);
    }
    public static void changeStage(String res) throws IOException {
        FXMLLoader loader = new FXMLLoader(HomeApplication.class.getResource(res));
        Scene sc = new Scene(loader.load());
        s.setScene(sc);
        if(res != "home-view.fxml"){
            setBackbutton(sc, "home-view.fxml");
        }
        if(res == "home-view.fxml")
            setImages(sc);
    }
    public static void main(String[] args) {
        launch();
    }
}