package com.leeco.smanager.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;

import com.leeco.smanager.receivers.LeecoPowerManager;


public class LPowerService extends Service {
    public LPowerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private LeecoPowerManager lreceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        final IntentFilter theFilter = new IntentFilter();
        theFilter.addAction(PowerManager.ACTION_POWER_SAVE_MODE_CHANGED);
    //    theFilter.addAction(PerformanceManager.POWER_PROFILE_CHANGED);
        this.lreceiver = new LeecoPowerManager();
        this.registerReceiver(this.lreceiver, theFilter);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.lreceiver);
    }
}
