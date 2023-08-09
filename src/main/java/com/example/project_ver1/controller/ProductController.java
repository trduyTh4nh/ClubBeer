package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.model.DB;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.PointLight;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.LineNumberInputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    public TableView<Product> tableProduct;
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

    @FXML
    TextField id_search;
    DB db = new DB();

    private int ID;

    public ProductController() throws SQLException {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addProduct() throws SQLException {

        Product product = new Product(Integer.parseInt(id_id.getText().toString()), id_name.getText().toString(), id_desc.getText().toString(), Integer.parseInt(id_cate.getText().toString()), Integer.parseInt(id_price.getText().toString()));
        db.insertProduct(product);
        getData();

        clearTextFeild();
    }
    public void clearTextFeild(){
        id_id.clear();
        id_name.clear();
        id_desc.clear();
        id_cate.clear();
        id_price.clear();
    }
    @FXML
    public ArrayList<Product> listData() throws SQLException {
        ArrayList<Product> arrProduct = new ArrayList<>();

        ResultSet r = db.getProduct();
        while (r.next()){
            arrProduct.add(new Product(r.getInt(1),r.getString(2), r.getString(3), r.getInt(4), r.getInt(5)));
        }
        return arrProduct;
    }
    @FXML
    public ArrayList<Product> listDataToSearch() throws SQLException {
        String key = id_search.getText().toString();
        ArrayList<Product> products = new ArrayList<>();
        ResultSet r = db.searchProduct(key);
        while (r.next()){
            products.add(new Product(r.getInt(1),r.getString(2), r.getString(3), r.getInt(4), r.getInt(5)));
        }
        return products;
    }

    public void getData() throws SQLException {
        initData();

        ArrayList<Product> list = listData();
        tableProduct.getItems().addAll(list);

    }
    public void getDataTable(ArrayList<Product> arrayList){
        initData();
        tableProduct.getItems().addAll(arrayList);
    }

    private void initData() {
        tableProduct.getItems().clear();
        masp.setCellValueFactory(new PropertyValueFactory<>("MaSP"));
        tensp.setCellValueFactory(new PropertyValueFactory<>("TenSP"));
        motasp.setCellValueFactory(new PropertyValueFactory<>("MoTa"));
        loaisp.setCellValueFactory(new PropertyValueFactory<>("MaLoaiSp"));
        giasp.setCellValueFactory(new PropertyValueFactory<>("Gia"));
    }

    @FXML
    private void ClickItems(){
            if(tableProduct.getSelectionModel().getSelectedItem() != null){
                ID = tableProduct.getSelectionModel().getSelectedItem().getMaSP();
                String tensp = tableProduct.getSelectionModel().getSelectedItem().getTenSP();
                String masp = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getMaSP());
                String mota = tableProduct.getSelectionModel().getSelectedItem().getMoTa();
                String loaiSp = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getMaLoaiSp());
                String gia = String.valueOf(tableProduct.getSelectionModel().getSelectedItem().getGia());

                id_id.setText(masp);
                id_name.setText(tensp);
                id_desc.setText(mota);
                id_cate.setText(loaiSp);
                id_price.setText(gia);

            }

    }

    @FXML
    private void searchProduct() throws SQLException {
        initData();
        ArrayList<Product> list = listDataToSearch();
        tableProduct.getItems().addAll(list);
    }

    @FXML
    private void edtProduct() throws SQLException {
        Product product = new Product(Integer.parseInt(id_id.getText().toString()), id_name.getText().toString(), id_desc.getText().toString(), Integer.parseInt(id_cate.getText().toString()), Integer.parseInt(id_price.getText().toString()));
        db.updateProduct(product);
        getData();
    }
    @FXML
    public void deleteProduct(){
        try{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Xác nhận xoá");
            a.setContentText("Bạm có muốn sản phẩm \"" + id_name.getText() + "\"?");
            a.setHeaderText("Xoá sản phẩm?");
            a.showAndWait();
            if(a.getResult() == ButtonType.OK){
                db.removeProduct(ID);
                getData();
            }
        } catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi CSDL");
            a.setContentText("Lỗi khi truy xuất CSDL: \n" + e.getMessage());
            a.setHeaderText("Lỗi SQL");
            a.show();
        } catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Lỗi");
            a.setContentText("Lỗi về phần mềm, có thể các ô có thể rỗng hoặc sai kiểu dữ liệu: \n" + e.getMessage());
            a.setHeaderText("Lỗi client");
            a.show();
        }

        clearTextFeild();
    }



}
