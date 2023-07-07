package com.example.project_ver1.class_model;

public class OrderDeltail {
    private int SoCT;
    private int IdHD;
    private int MaSP;

    public OrderDeltail(int soCT, int idHD, int maSP) {
        SoCT = soCT;
        IdHD = idHD;
        MaSP = maSP;
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
