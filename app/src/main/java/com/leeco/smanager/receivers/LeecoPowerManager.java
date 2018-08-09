package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import com.leeco.smanager.BuildConfig;
import com.topjohnwu.superuser.BusyBox;
import com.topjohnwu.superuser.Shell;

import java.util.Objects;

import cyanogenmod.power.PerformanceManager;


public class LeecoPowerManager extends BroadcastReceiver {
    public LeecoPowerManager(){}
    String TAG = "SManager: LeecoPM";
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR);
            Shell.Config.verboseLogging(BuildConfig.DEBUG);
            BusyBox.setup(context);
            final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (Objects.requireNonNull(pm).isPowerSaveMode()) {
                Log.d(TAG,"Native powersave mode on");
                Log.d(TAG,"Execute: /etc/mode/powersave.sh");
                Shell.su("sh /etc/mode/powersave.sh").submit();
            } else {
                Log.d(TAG,"Native powersave mode off ");
                Log.d(TAG,"Execute: /etc/mode/normal.sh");
                Shell.su("sh /etc/mode/normal.sh").submit();
            }
        }catch (Exception d){
            Log.d(TAG,d.getLocalizedMessage());
        }
        try{
            PerformanceManager pm = PerformanceManager.getInstance(context);
            Log.d(TAG, "CM PowerProfile : " + pm.getPowerProfile());
        }catch (Exception d){}

    }
}
