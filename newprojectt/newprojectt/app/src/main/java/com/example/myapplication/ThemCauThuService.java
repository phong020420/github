package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class ThemCauThuService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /////////////////////
        DAO dao = new DAO(this);
        Bundle bundlefromSCActivity = intent.getExtras();
        Cauthu cauthu = (Cauthu) bundlefromSCActivity.getSerializable("listne");
        if(dao.Themct(cauthu)) {
            Intent intentThembr = new Intent();
            Bundle bundleThembr = new Bundle();
            bundleThembr.putBoolean("CheckThem", true);
            intentThembr.putExtras(bundleThembr);
            intentThembr.setAction("ThemCt");
            sendBroadcast(intentThembr);

        }else{
            Intent intentThembr = new Intent();
            Bundle bundleThembr = new Bundle();
            bundleThembr.putBoolean("CheckThem", false);
            intentThembr.putExtras(bundleThembr);
            intentThembr.setAction("ThemCt");
            sendBroadcast(intentThembr);
        }


///////////////////////
        return super.onStartCommand(intent, flags, startId);
    }
}
