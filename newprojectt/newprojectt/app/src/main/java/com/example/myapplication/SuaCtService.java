package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class SuaCtService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DAO dao = new DAO(this);
        Bundle bundlefromSCActivity = intent.getExtras();
        Cauthu cauthu = (Cauthu) bundlefromSCActivity.getSerializable("modelsua");
        if(dao.capnhatct(cauthu)) {
            Intent intentThembr = new Intent();
            Bundle bundleThembr = new Bundle();
            bundleThembr.putBoolean("CheckSua", true);
            intentThembr.putExtras(bundleThembr);
            intentThembr.setAction("SuaCt");
            sendBroadcast(intentThembr);

        }else{
            Intent intentThembr = new Intent();
            Bundle bundleThembr = new Bundle();
            bundleThembr.putBoolean("CheckSua", false);
            intentThembr.putExtras(bundleThembr);
            intentThembr.setAction("SuaCt");
            sendBroadcast(intentThembr);
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
