package com.example.myapplication.DBHELPER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ASMDB";
    public static final int VERSION = 1;
    public static final String CREATTABLE_NGUOIDUNG = "create table SANPHAM" +
            "(Mact text primary key ," +
            "Tenct text," +
            "Ngaysinh text," +
            "Luong integer)";

    public static final String INSERT_THONGTIN ="insert into SANPHAM VALUES" +
            "('CT01', 'ACT',  '2000/01/06',20)," +
            "('CT02', 'BCT',  '2001/02/07',30)," +
            "('CT03', 'CCT',  '2002/03/08',40)," +
            "('CT04', 'DCT',  '2003/04/09',50)," +
            "('CT05', 'ECT',  '2004/05/10',60)" ;
    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATTABLE_NGUOIDUNG);
        db.execSQL(INSERT_THONGTIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists SANPHAM");
    }
}
