package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.leeco.smanager.services.LPowerService;

public class StartBoot extends BroadcastReceiver {
    public  String TAG = "SManger: Boot Complete";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Start method onReceive");
        SharedPreferences sPref;
        sPref = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        String state =  sPref.getString("WifiState","true");
        Log.d(TAG,state + " pref state for WiFi");
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if(state.equals("true")){
            wifiManager.setWifiEnabled(true);
        }
        else{
            wifiManager.setWifiEnabled(false);
        }
        Log.d(TAG,"Start service");
        context.startService(new Intent(context, LPowerService.class));
    }
}
