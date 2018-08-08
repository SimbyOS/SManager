package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import lineageos.power.PerformanceManager;


public class LeecoPowerManager extends BroadcastReceiver {
    String TAG = "LeecoPM";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Profile chenged to PROFILE_POWER_SAVE");
        try{
            PerformanceManager manager = PerformanceManager.getInstance(context);
            if(manager.getPowerProfile() == PerformanceManager.PROFILE_POWER_SAVE){
                Log.d(TAG,"Profile chenged to PROFILE_POWER_SAVE");
            }

            if(manager.getPowerProfile() == PerformanceManager.PROFILE_BALANCED){
                Log.d(TAG,"Profile chenged to PROFILE_BALANCED");
            }

            if(manager.getPowerProfile() == PerformanceManager.PROFILE_BIAS_POWER_SAVE){
                Log.d(TAG,"Profile chenged to PROFILE_BIAS_POWER_SAVE");
            }
            if(manager.getPowerProfile() == PerformanceManager.PROFILE_HIGH_PERFORMANCE){
                Log.d(TAG,"Profile chenged to PROFILE_HIGH_PERFORMANCE");
            }
            if(manager.getPowerProfile() == PerformanceManager.PROFILE_BIAS_PERFORMANCE){
                Log.d(TAG,"Profile chenged to PROFILE_BIAS_PERFORMANCE");
            }
        }catch (Exception d){
            Log.d(TAG,d.getMessage());
        }

        try{
            final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm.isPowerSaveMode()) {
                Log.d(TAG,"Native powersave mode on");
            } else {
                Log.d(TAG,"Native powersave mode off ");
            }
        }catch (Exception d){
            Log.d(TAG,d.getLocalizedMessage());
        }

    }
}
