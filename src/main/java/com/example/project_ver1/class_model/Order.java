package com.example.project_ver1.class_model;

public class Order {
    private int MaHD;
    private String NgayLap;



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
