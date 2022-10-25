package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CauThuService extends Service {
    ArrayList<Cauthu> list;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        ArrayList<Cauthu> list = new DAO(this).getallct();
        Intent intentgetallbr = new Intent();
        Bundle bundlegetallbr = new Bundle();
        bundlegetallbr.putSerializable("list",list);
        intentgetallbr.putExtras(bundlegetallbr);
        intentgetallbr.setAction("GetallCt");
        sendBroadcast(intentgetallbr);




        return super.onStartCommand(intent, flags, startId);
    }
}
