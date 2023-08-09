package com.example.project_ver1.class_model;

import javafx.scene.shape.StrokeLineCap;

import java.sql.Date;

public class Order {
    private int MaHD;
    private String NgayLap;
    private String TongTien;
    private String idUser;

    public Order(int maHD) {
        MaHD = maHD;
    }

    public Order(int maHD, String ngayLap, String tongTien, String idUser) {
        MaHD = maHD;
        NgayLap = ngayLap;
        TongTien = tongTien;
        this.idUser = idUser;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String tongTien) {
        TongTien = tongTien;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
        public Order(int maHD, String ngayLap) {
        MaHD = maHD;
        NgayLap = ngayLap;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

        public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }


}
