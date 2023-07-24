package com.example.project_ver1.controller;

import com.example.project_ver1.model.DB;

import java.sql.SQLException;

public class OrderController {
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
