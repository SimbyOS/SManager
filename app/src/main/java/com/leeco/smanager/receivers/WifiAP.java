package com.leeco.smanager.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.util.Log;

import com.leeco.smanager.BuildConfig;
import com.topjohnwu.superuser.BusyBox;
import com.topjohnwu.superuser.Shell;

import cc.mvdan.accesspoint.WifiApControl;

public class WifiAP extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        SystemClock.sleep(3000);
        String action = intent.getAction();
        Log.d("WifiAP",action);
        WifiApControl apControl = WifiApControl.getInstance(context);
        Log.d("WifiAP","Waiting thread");
        boolean enabled = apControl.isEnabled();
        if (!enabled){
            Log.d("WifiAP","AP disabled");
            Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR);
            Shell.Config.verboseLogging(BuildConfig.DEBUG);
            BusyBox.setup(context);
            Log.d("WifiAP","remove driver/insmod");
            Shell.su("rmmod wlan; insmod /system/lib/modules/pronto/pronto_wlan.ko").submit();
        }

    }
}
