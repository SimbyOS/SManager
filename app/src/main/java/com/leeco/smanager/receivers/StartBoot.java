package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.leeco.smanager.services.LeecoPowerService;

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
        Intent serviceIntent = new Intent();
        serviceIntent.setType(LeecoPowerService.ACTION_REGISTER_RECIEVER);
        serviceIntent.putExtra(LeecoPowerService.EXTRA_PARAM1,""); //А вдруг будем когда нить что нить передавать
        serviceIntent.putExtra(LeecoPowerService.EXTRA_PARAM2,"");
        context.startService(new Intent(context, LeecoPowerService.class));
    }
}
