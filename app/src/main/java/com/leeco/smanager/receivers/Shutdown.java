package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.leeco.smanager.services.LeecoPowerService;

public class Shutdown extends BroadcastReceiver {
    public  String TAG = "SManger: ShutdownLog";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"Start  onReceive method");
        SharedPreferences sPref;
        sPref = context.getSharedPreferences("shared",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        boolean state = checkWifiOnAndConnected(context);
        if(state)
            ed.putString("WifiState","true");
        else
            ed.putString("WifiState","false");

        ed.commit();
        Intent serviceIntent = new Intent();
        serviceIntent.setType(LeecoPowerService.ACTION_UNREGISTER_RECIEVER);
        serviceIntent.putExtra(LeecoPowerService.EXTRA_PARAM1,""); //А вдруг будем когда нить что нить передавать
        serviceIntent.putExtra(LeecoPowerService.EXTRA_PARAM2,"");
        serviceIntent.setClass(context,LeecoPowerService.class);
        context.startService(serviceIntent);
    }
    private boolean checkWifiOnAndConnected(Context d) {
        WifiManager wifiMgr = (WifiManager) d.getSystemService(Context.WIFI_SERVICE);

        if (wifiMgr.isWifiEnabled()) { // Wi-Fi adapter is ON
         return true;
        }
        else {
            return false; // Wi-Fi adapter is OFF
        }
    }
}
