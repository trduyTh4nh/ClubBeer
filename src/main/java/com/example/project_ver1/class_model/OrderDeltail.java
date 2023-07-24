package com.example.project_ver1.class_model;

public class OrderDeltail {
    public OrderDeltail(int soCT, int idHD, String tenSanPham, int soluong, String size) {
        SoCT = soCT;
        IdHD = idHD;
        TenSanPham = tenSanPham;
        Soluong = soluong;
        Size = size;
    }

    private int SoCT;
    private int IdHD;
    private String TenSanPham;
    private int Soluong;
    private String Size;

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

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
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
}
