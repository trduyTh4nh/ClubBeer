package com.example.project_ver1.class_model;

import java.util.Date;

public class Order {
    private int MaHD;
    private Date NgayLap;
    private int TongTien;

    public Order(int maHD, Date ngayLap, int tongTien) {
        MaHD = maHD;
        NgayLap = ngayLap;
        TongTien = tongTien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngayLap) {
        NgayLap = ngayLap;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }


}
