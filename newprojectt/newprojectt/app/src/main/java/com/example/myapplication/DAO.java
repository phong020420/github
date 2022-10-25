package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.DBHELPER.DBhelper;

import java.util.ArrayList;

public class DAO {
    DBhelper dBhelper;

    ArrayList<Cauthu> list;
    public DAO(Context context){
        dBhelper = new DBhelper(context);
    }

    public ArrayList<Cauthu> getallct(){
        list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SANPHAM",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                list.add(new Cauthu(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)));

            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean Themct(Cauthu model){
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Mact",model.getMact());
        contentValues.put("Tenct",model.getTenct());
        contentValues.put("Ngaysinh",model.getNgaysinh());
        contentValues.put("Luong",model.getLuong());
        long check = sqLiteDatabase.insert("SANPHAM", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }
    public boolean capnhatct(Cauthu model){
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Tenct",model.getTenct());
        contentValues.put("Ngaysinh",model.getNgaysinh());
        contentValues.put("Luong",model.getLuong());
        long check = sqLiteDatabase.update("SANPHAM",contentValues,"Mact = ?", new String[]{String.valueOf(model.getMact())});
        if(check == -1){
            return false;
        }
        return true;
    }
    public boolean xoact(String Mact){
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("SANPHAM","Mact = ?",new String[]{Mact});
        if(check == -1){
            return  false;
        }
        return true;
    }

    }


