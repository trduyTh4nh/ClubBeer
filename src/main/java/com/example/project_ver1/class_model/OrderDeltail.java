package com.example.project_ver1.class_model;

public class OrderDeltail {
    private int SoCT;
    private int IdHD;
    private int MaSP;
    private int Soluong;
    private int Size;

    public OrderDeltail(int soCT, int idHD, int maSP, int soluong, int size) {
        SoCT = soCT;
        IdHD = idHD;
        MaSP = maSP;
        Soluong = soluong;
        Size = size;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
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

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int maSP) {
        MaSP = maSP;
    }
}
