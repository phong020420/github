package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class XoaService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String ma = (String) bundle.getString("madexoa");

        DAO dao = new DAO(this);
        if(dao.xoact(ma)){
            Intent intentxoabr = new Intent();
            Bundle bundlexoabr = new Bundle();
            bundlexoabr.putBoolean("madexoanuane", true);
            intentxoabr.putExtras(bundlexoabr);
            intentxoabr.setAction("XoaCtbr");
            sendBroadcast(intentxoabr);
        }
        else {
            Intent intentxoabr = new Intent();
            Bundle bundlexoabr = new Bundle();
            bundlexoabr.putBoolean("madexoanuane", false);
            intentxoabr.putExtras(bundlexoabr);
            intentxoabr.setAction("XoaCtbr");
            sendBroadcast(intentxoabr);
        }




        return super.onStartCommand(intent, flags, startId);
    }
}
