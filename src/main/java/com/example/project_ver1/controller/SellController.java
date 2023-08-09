package com.example.project_ver1.controller;

import com.example.project_ver1.class_model.Order;
import com.example.project_ver1.class_model.OrderDeltail;
import com.example.project_ver1.class_model.Product;
import com.example.project_ver1.class_model.User;
import com.example.project_ver1.model.DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class SellController implements Initializable {

    @FXML
    TableView<Order> tbHoadon;
    @FXML
    TextField id_searchHoaDon;
    @FXML
    Button id_btSeachHoaDon;
    public TableColumn<Order, Integer> IDHoaDon;
    public TableColumn<Order, String> NgayLap;
    public TableColumn<Order,String> TongTien;
    public TableColumn<Order,String> TenNguoiLap;

    public SellController() throws SQLException{

    }
    DB db = new DB();
    private  int ID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        try {
            getData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void search() throws SQLException {
        initData();
        ArrayList<Order> list = listDataToSearch();
        tbHoadon.getItems().addAll(list);
    }

    @FXML
    public ArrayList<Order> listDataToSearch() throws SQLException {
        String key = id_searchHoaDon.getText().toString();
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet r = db.search(Integer.parseInt(key));
        while (r.next()){
            orders.add(new Order(r.getInt(1),r.getString(2), r.getString(3), r.getString(4)));
        }
        return orders;
    }

    @FXML
    public ArrayList<Order> listData() throws SQLException {
        ArrayList<Order> arrOrder = new ArrayList<>();

        ResultSet r = db.getOrder();

        while (r.next()) {
            int userID = Integer.parseInt(r.getString(4));
            String name = "";
            try (ResultSet rs = db.getUserbyID(userID)) {
                if (rs.next()) {
                    name  = rs.getString(2);
                    rs.close();
                }
            }
            arrOrder.add(new Order(r.getInt(1), r.getString(2), r.getString(3), name));

        }

        return arrOrder;
    }

    public void getData() throws SQLException {
        initData();
        ArrayList<Order> list = listData();
        tbHoadon.getItems().addAll(list);

    }
    private void initData() {
        tbHoadon.getItems().clear();
        IDHoaDon.setCellValueFactory(new PropertyValueFactory<>("MaHD"));
        NgayLap.setCellValueFactory(new PropertyValueFactory<>("NgayLap"));
        TongTien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));
        TenNguoiLap.setCellValueFactory(new PropertyValueFactory<>("idUser"));
    }
    @FXML
    private void ClickItemHoaDon(){
      if(tbHoadon.getSelectionModel().getSelectedItem() != null) {
          ID = tbHoadon.getSelectionModel().getSelectedItem().getMaHD();
//          String NgayLap= tbHoadon.getSelectionModel().getSelectedItem().getNgayLap();
//          String TongTien = tbHoadon.getSelectionModel().getSelectedItem().getTongTien();
//          String IdUser = tbHoadon.getSelectionModel().getSelectedItem().getIdUser();

          id_searchHoaDon.setText(String.valueOf(ID));

      }
    }
    @FXML
    public void deleteHoaDon(){
        try{
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Xác nhận xoá");
            a.setContentText("Bạm có muốn hóa đơn ?");
            a.setHeaderText("Xoá hóa đơn?");
            a.showAndWait();
            if(a.getResult() == ButtonType.OK){
                db.removeHoaDon(ID);
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

    }
}
