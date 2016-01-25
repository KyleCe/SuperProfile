package com.product.kyle.testforgradle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/*
* 实际上是切换状态
* */
public class AlarmReceiverStop extends BroadcastReceiver {


    final static int REPEAT_MODE_EVERYDAY = 1;
    final static int REPEAT_MODE_WORKDAY = 2;
    final static int REPEAT_MODE_WEEKEND = 3;

    public AlarmReceiverStop() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

//            context.stopService(new Intent("com.product.kyle.ApplyProfileOne"));

//        context.startService(new Intent("com.product.kyle.ApplyProfileOne"));


//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("KEY_VOLUME_OLD", audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));
//        editor.putInt("KEY_WIFI_OLD", wifiManager.getWifiState());//wifi state: see API guide
//        editor.putInt("KEY_RINGER_MODE_OLD", audioManager.getRingerMode());
//        editor.putBoolean("KEY_BLUETOOTH_OLD", bluetoothAdapter.isEnabled());
//        editor.putBoolean("KEY_GPRS_OLD", getMobileDataStatus("getMobileDataEnabled"));//unsure of its working effect
//        try {
//            editor.putInt("KEY_BRIGHTNESS_OLD", Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS));
//            editor.putBoolean("KEY_BRIGHTNESS_MODE_OLD", Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC ==
//                    Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        editor.commit();




    }
}
