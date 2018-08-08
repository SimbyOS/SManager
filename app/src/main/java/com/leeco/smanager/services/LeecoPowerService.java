package com.leeco.smanager.services;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;


public class LeecoPowerService extends IntentService {
   public String TAG = "SManager:  LeecoPowerManagerService";
    public static final String ACTION_REGISTER_RECIEVER = "com.leeco.smanager.action.register";
    public static final String ACTION_UNREGISTER_RECIEVER = "com.leeco.smanager.action.unregister";
    public static final String EXTRA_PARAM1 = "com.leeco.smanager.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.leeco.smanager.extra.PARAM2";
    public LeecoPowerService() {
        super("LeecoPowerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_REGISTER_RECIEVER.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                registerReciever(param1, param2);
            } else if (ACTION_UNREGISTER_RECIEVER.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                unregisterReciever(param1, param2);
            }
        }
    }
   private BroadcastReceiver powerSaverChangeReceiver;
    private void registerReciever(String param1, String param2) {
         powerSaverChangeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                if (pm.isPowerSaveMode()) {
                    Log.d(TAG,"Android native powersave mode : on");
                } else {
                    Log.d(TAG,"Android native powersave mode : off");
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.os.action.POWER_SAVE_MODE_CHANGING");
        registerReceiver(powerSaverChangeReceiver, filter);
    }

    private void unregisterReciever(String param1, String param2) {
    unregisterReceiver(powerSaverChangeReceiver);
    }
}
