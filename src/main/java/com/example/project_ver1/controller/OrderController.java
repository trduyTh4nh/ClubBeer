package com.example.project_ver1.controller;

import com.example.project_ver1.HomeApplication;
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
import javafx.stage.DirectoryChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private int IdHD;
    @FXML
    Label total;
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
    @FXML
    TableColumn<OrderDeltail, Integer> id_col_gia;
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

        Order order = new Order(-1, dt);
        IdHD = db.insertOrder(order).getMaHD();
        id_SoHoaDon.setText(String.valueOf(IdHD));
        tb_ordetail.getItems().clear();

    }
    private void updateTotalPrice() throws SQLException {
        int tongTien = 0;
        ResultSet set = db.getGiaFromHoaDon(IdHD);
        double price = 0;
        while (set.next()){
            int sl = set.getInt("soluong");
            price = set.getInt("gia") * sl;
            System.out.println(" " + sl + " " + price);
            switch (set.getString("size")){
                case "M":
                    price = price + price * 0.1;
                    break;
                case "L":
                    price = price + price * 0.25;
                    break;
                default:
                    break;
            }
            tongTien += price;
        }
        if(IdHD > 0){
            db.updateHoaDon(IdHD, tongTien);
        }
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
        tb_ordetail.getItems().clear();
        updateTotalPrice();
        getData();

    }
    @FXML
    public void doneBill() throws SQLException, IOException {
        String hdh = System.getProperty("os.name");
        System.out.println(hdh);
        char p;
        if(hdh.contains("win")){
            p = '\\';
        } else {
            p = '/';
        }
        DirectoryChooser c = new DirectoryChooser();
        File sel = c.showDialog(id_addProduct.getParent().getScene().getWindow());
        System.out.println(sel.getAbsolutePath().toString());
        File out = new File(sel.getAbsolutePath() + p + "hoadon"+IdHD+".txt");

        String fileName = sel.getAbsolutePath() + p + "hoadon"+IdHD+".txt";
        Path newFile = Paths.get(fileName);
        Files.createFile(newFile);
        StringBuilder s = new StringBuilder("Hoá đơn bán hàng");
        s.append(String.format("\n %10s %10s %15s %10s %10s %10s", "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Size"));
        ArrayList<OrderDeltail> l = iNeedMoreDETAILS(Integer.parseInt(id_SoHoaDon.getText()));
        int tongtien = 0;
        for (OrderDeltail d : l) {
            s.append(String.format("\n %10d %10d %15s %10d %10s %10s", d.getSoCT(), d.getIdsp(), d.getTenSP(), d.getSoluong(), d.getDongia(), d.getSize()));
            tongtien += d.getDongia();
        }
        s.append("\n===================================================================");
        s.append(String.format("\n Tổng tiền: %d", tongtien));
        s.append("\n===================================================================");
        LocalDateTime date = LocalDateTime.now().plusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String dt = format.format(date);
        s.append("\nCông ty TNHH ClubBeer, Hoá đơn xuất ngày " + dt);
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        writer.write(s.toString());
        writer.close();
        tb_ordetail.getItems().clear();
        getData();
    }
    public ArrayList<OrderDeltail> iNeedMoreDETAILS(int id) throws SQLException {
        ArrayList<OrderDeltail> d = new ArrayList<>();
        db = new DB();
        ResultSet set = db.getorderDetailbyIDHoaDon(id);
        while(set.next()){
            double price = set.getInt("dongia");
            switch (set.getString("size")){
                case "M":
                    price = price + price * 0.1;
                    break;
                case "L":
                    price = price + price * 0.25;
                    break;
                default:
                    break;
            }
            d.add(new OrderDeltail(set.getInt(1), set.getInt("masp"), set.getInt("soluong"), set.getString("tensp"), (int) price, set.getString("size")));
        }
        return d;
    }
    public void getData() throws SQLException {
        tb_ordetail.getItems().clear();
        ArrayList<OrderDeltail> list = getAllOrderDetail();
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

    public ArrayList<OrderDeltail> getAllOrderDetail() throws SQLException{
        int totalPrice = 0;
        double pricetotal = 0;
        int hoadon = -1;
        ArrayList<OrderDeltail> arr = new ArrayList<>();
        ResultSet set = db.getAllOrderDetail();
        while(set.next()){
            double price = set.getInt(6);
            hoadon = set.getInt("id");
            totalPrice += price;
            switch (set.getString(5)){
                case "M":
                    price = price + price * 0.1;
                    break;
                case "L":
                    price = price + price * 0.25;
                    break;
                default:
                    break;
            }
            arr.add(new OrderDeltail(set.getInt(1), set.getInt(2), set.getInt(4), set.getString(3), (int) (price * set.getInt(4)), set.getString(5)));
            pricetotal += (price * set.getInt(4));
        }

        total.setText(String.valueOf(pricetotal));
        return arr;
    }
    public void clearTextFeild(){
        id_SoHoaDon.clear();
        id_tenSP.clear();
        id_gia.clear();
        id_Soluong.clear();

    }
    @FXML
    public void dangXuat() throws IOException {
        HomeApplication.changeStage("login-view.fxml");
    }
    public void initData(){
        tb_ordetail.getItems().clear();
        id_colSoCT.setCellValueFactory(new PropertyValueFactory<>("SoCT"));
        id_ColSoHD.setCellValueFactory(new PropertyValueFactory<>("idsp"));
        id_ColMaSp.setCellValueFactory(new PropertyValueFactory<>("TenSP"));
        id_ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("Soluong"));
        id_ColSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
        id_col_gia.setCellValueFactory(new PropertyValueFactory<>("dongia"));
    }
    @FXML
    public void deleteOrder() throws SQLException {
        if (tb_ordetail.getSelectionModel().getSelectedItem() != null) {
            Product product = db.getProductById(tb_ordetail.getSelectionModel().getSelectedItem().getIdsp());
            String TenSP = product.getTenSP();
            int soCt = tb_ordetail.getSelectionModel().getSelectedItem().getSoCT();
            try {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Xác nhận xoá");
                a.setContentText("Bạm có muốn xóa sản phẩm \"" + TenSP + "\"?");
                a.setHeaderText("Xoá sản phẩm?");
                a.showAndWait();
                if (a.getResult() == ButtonType.OK) {
                    db.deleteDetailOrder(soCt);

                    getData();
                }
            } catch (SQLException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Lỗi CSDL");
                a.setContentText("Lỗi khi truy xuất CSDL: \n" + e.getMessage());
                a.setHeaderText("Lỗi SQL");
                a.show();
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Lỗi");
                a.setContentText("Lỗi về phần mềm, có thể các ô có thể rỗng hoặc sai kiểu dữ liệu: \n" + e.getMessage());
                a.setHeaderText("Lỗi client");
                a.show();
            }

            clearTextFeild();
        }


    }
}
