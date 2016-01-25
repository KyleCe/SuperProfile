package com.product.kyle.testforgradle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {


    final static int REPEAT_MODE_EVERYDAY = 1;
    final static int REPEAT_MODE_WORKDAY = 2;
    final static int REPEAT_MODE_WEEKEND = 3;

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.


        SharedPreferences preferences = context.getSharedPreferences("SuperProfileSP", Context.MODE_PRIVATE);
        //get repeat mode
        ProfileDetailFragment detailOneFragment = new ProfileDetailFragment();
        int repeat_mode = preferences.getInt(detailOneFragment.KEY_USER_REPEAT_MODE
                , detailOneFragment.default_repeat_mode);

        boolean startFlag = false;
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.DAY_OF_WEEK;
        switch (repeat_mode) {
            case REPEAT_MODE_EVERYDAY:
                startFlag = true;
                break;
            case REPEAT_MODE_WORKDAY:
                if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                    startFlag = true;
                }
                break;
            case REPEAT_MODE_WEEKEND:
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    startFlag = true;
                }
                break;
        }
        if (startFlag==true) {
            context.startService(new Intent("com.product.kyle.ApplyProfileOne"));
        }

    }
}
