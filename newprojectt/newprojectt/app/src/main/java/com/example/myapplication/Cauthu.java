package com.example.myapplication;

import java.io.Serializable;

public class Cauthu implements Serializable {
    String Mact;
    String Tenct;

    public Cauthu(String tenct, String ngaysinh, int luong) {
        Tenct = tenct;
        Ngaysinh = ngaysinh;
        Luong = luong;
    }

    String Ngaysinh;
    int Luong;

    public Cauthu() {
    }

    public Cauthu(String mact, String tenct, String ngaysinh, int luong) {
        Mact = mact;
        Tenct = tenct;
        Ngaysinh = ngaysinh;
        Luong = luong;
    }



    public String getMact() {
        return Mact;
    }

    public void setMact(String mact) {
        Mact = mact;
    }

    public String getTenct() {
        return Tenct;
    }

    public void setTenct(String tenct) {
        Tenct = tenct;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int luong) {
        Luong = luong;
    }
}
