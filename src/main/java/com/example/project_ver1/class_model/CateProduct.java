package com.example.project_ver1.class_model;

public class CateProduct {
    private int Maloai;
    private String TenSp;
    private String MoTa;

    public CateProduct(int maloai, String tenSp, String moTa) {
        Maloai = maloai;
        TenSp = tenSp;
        MoTa = moTa;
    }

    public int getMaloai() {
        return Maloai;
    }

    public void setMaloai(int maloai) {
        Maloai = maloai;
    }

    public String getTenSp() {
        return TenSp;
    }

    public void setTenSp(String tenSp) {
        TenSp = tenSp;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    @Override
    public String toString() {
        return TenSp;
    }
}
