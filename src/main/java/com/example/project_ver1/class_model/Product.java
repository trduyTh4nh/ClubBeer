package com.example.project_ver1.class_model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Product implements List<Product>  {


    private String MaSP;
    private String TenSP;
    private String MoTa;
    private String MaLoaiSp;

    private int Gia;

    public Product(String maSP, String tenSP, String moTa, String maLoaiSp, int gia) {
        MaSP = maSP;
        TenSP = tenSP;
        MoTa = moTa;
        MaLoaiSp = maLoaiSp;
        Gia = gia;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String maSP) {
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Product> iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Product product) {
        return false;
    }


    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }


    @Override
    public boolean addAll(Collection<? extends Product> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Product> c) {
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Product get(int index) {
        return null;
    }


    @Override
    public Product set(int index, Product element) {
        return null;
    }

    @Override
    public void add(int index, Product element) {

    }

    @Override
    public Product remove(int index) {
        return null;
    }


    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Product> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Product> listIterator(int index) {
        return null;
    }

    @Override
    public List<Product> subList(int fromIndex, int toIndex) {
        return null;
    }
}


