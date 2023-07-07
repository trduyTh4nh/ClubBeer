package com.example.project_ver1.class_model;

public class Product {
    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getMaLoaiSp() {
        return MaLoaiSp;
    }

    public void setMaLoaiSp(String maLoaiSp) {
        MaLoaiSp = maLoaiSp;
    }

    public Product(int maSP, String tenSP, String moTa, String maLoaiSp) {
        MaSP = maSP;
        TenSP = tenSP;
        MoTa = moTa;
        MaLoaiSp = maLoaiSp;
    }

    private int MaSP;
    private String TenSP;
    private String MoTa;
    private String MaLoaiSp;

}
