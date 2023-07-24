package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Order;
import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.model.DB;
import com.example.project_ver1.model.LoginDetails;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderController implements Initializable {
    @FXML
    GridPane grid;
    DB db;
    ArrayList<Product> prods;
    private int ID;
    @FXML
    TextField id_SoHoaDon;
    @FXML
    TextField id_tenSP;
    @FXML
    TextField id_gia;
    @FXML
    TextField id_Soluong;
    @FXML
    ComboBox<String> id_size;
    @FXML
    Button id_addProduct;
    private LoginDetails loginDetails = LoginDetails.INSTANCE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_size.setItems(FXCollections.observableArrayList("S", "M", "L", "XL"));
        id_addProduct.setDisable(true);
        try {
            db = new DB();
            prods = new ArrayList<>();
            getProds();
            int k = 0;
            boolean isEven;
            int size;
            if(prods.size() % 3 == 0){
                size = prods.size() / 3;
                isEven = true;
            } else {
                size = prods.size() / 3 + 1;
                isEven = false;
            }
            for(int i = 0; i < size; i++){
                for(int j = 0; j < 3; j++){
                    if(!isEven){
                        if(i == size - 1){
                            if(j == prods.size() % 3){
                                break;
                            }
                        }
                    }
                    Product prod = prods.get(k);
                    ToggleButton btn = new ToggleButton(prod.getTenSP());
                    btn.setPrefWidth(133);
                    btn.setPrefHeight(53);
                    btn.setOnAction(e -> {
                        ID = prod.getMaSP();
                        id_tenSP.setText(prod.getTenSP());
                        id_gia.setText(String.valueOf(prod.getGia()));
                    });
                    grid.add(btn, j, i);
                    k++;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getProds() throws SQLException{
        ResultSet product = db.getProduct();
        while(product.next()){
            prods.add(new Product(product.getInt(1), product.getString(2), product.getString(3), product.getInt(4), product.getInt(5)));
        }
    }
    public OrderController() throws SQLException {

    }
    public void createOrder() throws SQLException {
        db = new DB();
        id_addProduct.setDisable(false);
        String idHD = id_SoHoaDon.getText().toString();
        String nameProduct = id_tenSP.getText().toString();
        String giaProduct = id_gia.getText().toString();
        String soluongProduct = id_Soluong.getText().toString();
        String Size = id_size.getItems().toString();

        LocalDateTime date = LocalDateTime.now().plusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        String dt = format.format(date);

        Order order = new Order(Integer.parseInt(idHD), dt);
        db.insertOrder(order);


    }

}
