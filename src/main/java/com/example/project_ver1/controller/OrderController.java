package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Order;
import com.example.project_ver1.class_model.OrderDeltail;
import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.model.DB;
import com.example.project_ver1.model.LoginDetails;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    TableView<OrderDeltail> tb_ordetail;
    @FXML
    TableColumn<OrderDeltail, Integer> id_colSoCT;
    @FXML
    TableColumn<OrderDeltail, Integer> id_ColSoHD;
    @FXML
    TableColumn<OrderDeltail, Integer> id_ColMaSp;
    @FXML
    TableColumn<OrderDeltail, Integer> id_ColSoLuong;
    @FXML
    TableColumn<OrderDeltail, String> id_ColSize;

    private LoginDetails loginDetails = LoginDetails.INSTANCE;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_size.setItems(FXCollections.observableArrayList("S", "M", "L", "XL"));
        id_addProduct.setDisable(true);
        initData();

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

        try {
            getData();
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
        String Size = id_size.getValue();
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        System.out.println(Size);
        String dt = format.format(date);

        Order order = new Order(Integer.parseInt(idHD), dt);
        db.insertOrder(order);


    }
    private ArrayList<OrderDeltail> arrayListDetail;
    public void addProduct() throws SQLException {
        arrayListDetail = new ArrayList<>();
        db = new DB();
        String idHD = id_SoHoaDon.getText().toString();
        String nameProduct = id_tenSP.getText().toString();
        String giaProduct = id_gia.getText().toString();
        String soluongProduct = id_Soluong.getText().toString();
        String Size = id_size.getValue();
        System.out.println(Size);
        OrderDeltail orderDeltail = new OrderDeltail(0, Integer.parseInt(idHD), ID, Integer.parseInt(soluongProduct), Size);
        db.inserdOrderDetail(orderDeltail);


    }

    public void doneBill() throws SQLException {
        db = new DB();
        for(int i = 0; i < arrayListDetail.size(); i++){
            System.out.println(arrayListDetail.get(i));
        }
    }
    public void getData() throws SQLException {
        ArrayList<OrderDeltail> list = getListOrdetail();
        tb_ordetail.getItems().addAll(list);

    }
    public ArrayList<OrderDeltail> getListOrdetail() throws SQLException {
        ArrayList<OrderDeltail> list = new ArrayList<>();
        ResultSet set = db.getOrderdetail();
        while (set.next()){
            list.add(new OrderDeltail(set.getInt(1), set.getInt(2), set.getInt(3), set.getInt(4), set.getString(5)));
        }

        return list;
    }

    public void clearTextFeild(){
        id_SoHoaDon.clear();
        id_tenSP.clear();
        id_gia.clear();
        id_Soluong.clear();

    }

    public void initData(){
        tb_ordetail.getItems().clear();
        id_colSoCT.setCellValueFactory(new PropertyValueFactory<>("SoCT"));
        id_ColSoHD.setCellValueFactory(new PropertyValueFactory<>("IdHD"));
        id_ColMaSp.setCellValueFactory(new PropertyValueFactory<>("idsp"));
        id_ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("Soluong"));
        id_ColSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
    }

}
