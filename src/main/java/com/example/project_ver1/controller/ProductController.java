package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.model.DB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.LineNumberInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public TableView tableProduct;
    public TableColumn<Product, Integer> masp;
    public TableColumn<Product, String> tensp;
    public TableColumn<Product, String> motasp;
    public TableColumn<Product, String> loaisp;
    public TableColumn<Product, String> giasp;


    @FXML
    TextField id_id;

    @FXML
    TextField id_name;

    @FXML
    TextField id_cate;

    @FXML
    TextField id_price;

    @FXML
    TextField id_desc;


    public ProductController() throws SQLException {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void addProduct(){
        TableView tableView = new TableView();
        masp.setCellValueFactory(new PropertyValueFactory<>("MaSP"));
        tensp.setCellValueFactory(new PropertyValueFactory<>("TenSP"));
        motasp.setCellValueFactory(new PropertyValueFactory<>("MoTa"));
        loaisp.setCellValueFactory(new PropertyValueFactory<>("MaLoaiSp"));
        giasp.setCellValueFactory(new PropertyValueFactory<>("Gia"));

        Product product = new Product(id_id.getText().toString(), id_name.getText().toString(), id_desc.getText().toString(), id_cate.getText().toString(), Integer.parseInt(id_price.getText().toString()));
        tableProduct.getItems().add(product);

        id_id.clear();
        id_name.clear();
        id_desc.clear();
        id_cate.clear();
        id_price.clear();
    }
}
