package com.example.project_ver1.class_model;

public class OrderDeltail {


    private int SoCT;
    private int IdHD;
    private int idsp;
    private int Soluong;
    private String TenSP;
    private int dongia;


    public OrderDeltail(int soCT, int idsp, int soluong, String tenSP, int dongia, String size) {
        SoCT = soCT;
        this.idsp = idsp;
        Soluong = soluong;
        TenSP = tenSP;
        this.dongia = dongia;
        Size = size;
    }

    private String Size;

    public OrderDeltail(int soCT, int idHD, int idsp, int soluong, String size) {
        SoCT = soCT;
        IdHD = idHD;
        this.idsp = idsp;
        Soluong = soluong;
        Size = size;
    }

    public int getSoCT() {
        return SoCT;
    }

    public void setSoCT(int soCT) {
        SoCT = soCT;
    }

    public int getIdHD() {
        return IdHD;
    }

    public void setIdHD(int idHD) {
        IdHD = idHD;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
}
