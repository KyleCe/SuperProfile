package com.product.kyle.testforgradle;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ProfileFragment extends Fragment {
    private ProfileDetailFragment detailOneFragment;
    //shared preference
    SharedPreferences preferences;

    final static int RINGER_MODE_NORMAL = 1;
    final static int RINGER_MODE_SILENT = 2;
    final static int RINGER_MODE_VIBRATE = 3;

    final static int NOT_SET = -1;

    private AudioManager audioManager = null;
    BluetoothAdapter bluetoothAdapter = null;
    Intent intent;

    //service try,alarm try
    AlarmManager aManager;
    AlarmManager aManagerTest;

    WifiManager wifiManager = null;//Wifi


    //Brightness

    private static final String TAG = "ScreenLuminance";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);//关联布局文件

        Button myButton1 = (Button) rootView.findViewById(R.id.profile1);//根据rootView找到button
        Button myButton2 = (Button) rootView.findViewById(R.id.profile2);//根据rootView找到button
        Button myButton3 = (Button) rootView.findViewById(R.id.profile3);//根据rootView找到button
        Button myButton4 = (Button) rootView.findViewById(R.id.profile4);//根据rootView找到button

        ////change to toggle button
        ToggleButton toggleButtonProfile1 = (ToggleButton) rootView.findViewById(R.id.profile1_switcher_toggle);
        ToggleButton toggleButtonProfile2 = (ToggleButton) rootView.findViewById(R.id.profile2_switcher_toggle);
        ToggleButton toggleButtonProfile3 = (ToggleButton) rootView.findViewById(R.id.profile3_switcher_toggle);
        ToggleButton toggleButtonProfile4 = (ToggleButton) rootView.findViewById(R.id.profile4_switcher_toggle);

        //apply shared preferences change
//        Button applyProfile1 = (Button) rootView.findViewById(R.id.apply_profile_one);//根据rootView找到button
//        Button stopProfile1 = (Button) rootView.findViewById(R.id.stop_profile_one);//根据rootView找到button

        preferences = getActivity().getSharedPreferences("SuperProfileSP", Context.MODE_PRIVATE);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);//wifi
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        intent = new Intent();

        //统一监听Button
        OnClickListener ocl = new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.profile1:
                        preferences.edit().putInt("ProfileTabId", 1).commit();
                        detailOneFragment = new ProfileDetailFragment();
                        getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragContainer, detailOneFragment, "ONE_FRAGMENT").commit();
                        getChildFragmentManager().executePendingTransactions();
                        getFragmentManager().beginTransaction().addToBackStack(null).commit();

                        //Profile one first launch record
                        if (NOT_SET != preferences.getInt("FIRST_LAUNCH_FLAG", NOT_SET)) {
                            preferences.edit().putInt("FIRST_LAUNCH_FLAG", 1).commit();
                        }
                        break;
                    case R.id.profile2:
                        preferences.edit().putInt("ProfileTabId", 2).commit();
                        detailOneFragment = new ProfileDetailFragment();
                        getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragContainer, detailOneFragment, "ONE_FRAGMENT").commit();
                        getChildFragmentManager().executePendingTransactions();
                        getFragmentManager().beginTransaction().addToBackStack(null).commit();
                        break;
                    case R.id.profile3:
                        preferences.edit().putInt("ProfileTabId", 3).commit();
                        detailOneFragment = new ProfileDetailFragment();
                        getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragContainer, detailOneFragment, "ONE_FRAGMENT").commit();
                        getChildFragmentManager().executePendingTransactions();
                        getFragmentManager().beginTransaction().addToBackStack(null).commit();
                        break;
                    case R.id.profile4:
                        preferences.edit().putInt("ProfileTabId", 4).commit();
                        detailOneFragment = new ProfileDetailFragment();
                        getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragContainer, detailOneFragment, "ONE_FRAGMENT").commit();
                        getChildFragmentManager().executePendingTransactions();
                        getFragmentManager().beginTransaction().addToBackStack(null).commit();
                        break;
//                    case R.id.apply_profile_one:
//                        //服务方式，设置自动切换Profile one，涉及语句为 存当前、设置Profile one；
////                        SaveCurrentProfileDetail();
//                        ProfileOneApply();
////                        PeriodWork();
//                        break;
//                    case R.id.stop_profile_one:
//                        try {
//                            PendingIntent pendingIntent1 = PendingIntent.getService(getActivity(), 123,
//                                    new Intent(getActivity(), AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
//                            aManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
//                            aManager.cancel(pendingIntent1);
//                            getActivity().stopService(new Intent("com.product.kyle.testforgradle.APPLY_PROFILE_ONE_SERVICE"));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        break;
                    default:
                        preferences.edit().putInt("ProfileTabId", NOT_SET).commit();
                        break;
                }
            }

        };
        myButton1.setOnClickListener(ocl);
        myButton2.setOnClickListener(ocl);
        myButton3.setOnClickListener(ocl);
        myButton4.setOnClickListener(ocl);
//        applyProfile1.setOnClickListener(ocl);
//        stopProfile1.setOnClickListener(ocl);

        CompoundButton.OnCheckedChangeListener occ = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                switch (compoundButton.getId()) {
                    case R.id.profile1_switcher_toggle:
                        if (isChecked) {
                            ProfileOneApply();
                        } else {
                            try {
                                PendingIntent pendingIntent1 = PendingIntent.getService(getActivity(), 123,
                                        new Intent(getActivity(), AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
                                aManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
                                aManager.cancel(pendingIntent1);
                                getActivity().stopService(new Intent("com.product.kyle.testforgradle.APPLY_PROFILE_ONE_SERVICE"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case R.id.profile2_switcher_toggle:
                        if (isChecked) {
//                            Toast.makeText(ProfileFragment.this.getActivity(), "test of profile 2 toggle ", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.profile3_switcher_toggle:
                        break;
                    case R.id.profile4_switcher_toggle:
                        break;
                    default:
                        break;
                }
            }
        };

        toggleButtonProfile1.setOnCheckedChangeListener(occ);
        toggleButtonProfile2.setOnCheckedChangeListener(occ);
        toggleButtonProfile3.setOnCheckedChangeListener(occ);
        toggleButtonProfile4.setOnCheckedChangeListener(occ);
        //toggle button clickListener

        return rootView;
    }

    private void PeriodWork() {

        detailOneFragment = new ProfileDetailFragment();
        // for start
        int hour = preferences.getInt("KEY_USER_HOUR", detailOneFragment.default_hour);
        int minute = preferences.getInt("KEY_USER_MINUTE", detailOneFragment.default_minute);

        Intent intentAlarmReceiver = new Intent(getActivity(), AlarmReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 123, intentAlarmReceiver, 0);
        aManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + setTriggerMilliseconds(hour, minute),
                24 * 60 * 60 * 1000, pendingIntent);


        //for stop
        int hour_stop = preferences.getInt("KEY_USER_HOUR_STOP", detailOneFragment.default_hour_stop);
        int minute_stop = preferences.getInt("KEY_USER_MINUTE_STOP", detailOneFragment.default_minute_stop);
        Intent intentAlarmReceiverStop = new Intent(getActivity(), AlarmReceiverStop.class);
        final PendingIntent pendingIntentStop = PendingIntent.getService(getActivity(), 124, intentAlarmReceiverStop, 0);
        aManager = (AlarmManager) getActivity().getSystemService(Service.ALARM_SERVICE);
        aManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + setTriggerMilliseconds(hour, minute)
                        + gapMilliseconds(hour, minute, hour_stop, minute_stop),
                24 * 60 * 60 * 1000, pendingIntentStop);

    }

    private long gapMilliseconds(int hour, int minute, int hour_stop, int minute_stop) {

        int gapH;
        int gapM;

        if (hour_stop > hour) {
            gapH = hour_stop - hour - (minute_stop >= minute ? 0 : 1);
            gapM = (minute_stop > minute ? 0 : 60) + minute_stop - minute;
        } else if (hour_stop == hour) {
            gapH = 24 - (minute_stop > minute ? 24 : 1);
            gapM = (minute_stop > minute ? 0 : 60) + minute_stop - minute;
        } else {
            gapH = hour_stop - hour - (minute_stop >= minute ? 0 : 1);
            gapM = (minute_stop > minute ? 0 : 60) + minute_stop - minute;
        }
        return (gapH * 60 + gapM) * 60 * 1000;
    }

    private void SaveCurrentProfileDetail() {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("KEY_VOLUME_OLD", audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM));
        editor.putInt("KEY_WIFI_OLD", wifiManager.getWifiState());//wifi state: see API guide
        editor.putInt("KEY_RINGER_MODE_OLD", audioManager.getRingerMode());

        // FIXME: 2016/1/27
//        editor.putBoolean("KEY_BLUETOOTH_OLD", bluetoothAdapter.isEnabled());
        editor.putBoolean("KEY_GPRS_OLD", getMobileDataStatus("getMobileDataEnabled"));//unsure of its working effect
        try {
            editor.putInt("KEY_BRIGHTNESS_OLD", Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS));
            editor.putBoolean("KEY_BRIGHTNESS_MODE_OLD", Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC ==
                    Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    /**
     * 计算距离下一次发送的时间
     *
     * @param hour   小时
     * @param minute 分钟
     * @return
     */
    private static long setTriggerMilliseconds(int hour, int minute) {
        Calendar c = Calendar.getInstance();
        int currentH = c.get(Calendar.HOUR_OF_DAY);
        int currentM = c.get(Calendar.MINUTE);
        int setH = 0;
        int setM = 0;

        if (hour == currentH) {
            if (minute >= currentM) {
                setH = 0;
                setM = minute - currentM;
            } else if (minute < currentM) {
                setH = 24 - 1;
                setM = currentM - minute;
            }
        } else if (hour > currentH) {
            if (minute >= currentM) {
                setH = hour - currentH;
                setM = minute - currentM;
            } else if (minute < currentM) {
                setH = hour - currentH - 1;
                setM = minute + (60 - currentM);
            }
        } else if (hour < currentH) {
            if (minute >= currentM) {
                setH = 24 - currentH + hour;
                setM = minute - currentM;
            } else if (minute < currentM) {
                setH = 24 - currentH + hour - 1;
                setM = minute + (60 - currentM);
            }
        }

        return setH * 60 * 60 * 1000 + setM * 60 * 1000;
    }


    /*
    * APN reflect
     * 移动数据开启和关闭  测试通过
    * */
    public void setMobileDataStatus(Context context, boolean enabled) {
        ConnectivityManager conMgr = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        //ConnectivityManager类

        Class<?> conMgrClass = null;

        //ConnectivityManager类中的字段
        Field iConMgrField = null;
        //IConnectivityManager类的引用
        Object iConMgr = null;
        //IConnectivityManager类
        Class<?> iConMgrClass = null;
        //setMobileDataEnabled方法
        Method setMobileDataEnabledMethod = null;
        try {

            //取得ConnectivityManager类
            conMgrClass = Class.forName(conMgr.getClass().getName());
            //取得ConnectivityManager类中的对象Mservice
            iConMgrField = conMgrClass.getDeclaredField("mService");
            //设置mService可访问
            iConMgrField.setAccessible(true);
            //取得mService的实例化类IConnectivityManager
            iConMgr = iConMgrField.get(conMgr);
            //取得IConnectivityManager类
            iConMgrClass = Class.forName(iConMgr.getClass().getName());

            //取得IConnectivityManager类中的setMobileDataEnabled(boolean)方法
            setMobileDataEnabledMethod = iConMgrClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);

            //设置setMobileDataEnabled方法是否可访问
            setMobileDataEnabledMethod.setAccessible(true);
            //调用setMobileDataEnabled方法
            setMobileDataEnabledMethod.invoke(iConMgr, enabled);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (NoSuchFieldException e) {

            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();

        } catch (NoSuchMethodException e)

        {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        } catch (InvocationTargetException e)

        {

            e.printStackTrace();

        }

    }

    /*
    * APN reflect
     * 移动数据状态获取  测试通过
    * */
    public boolean getMobileDataStatus(String getMobileDataEnabled)

    {

        ConnectivityManager cm;

        cm = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        Class cmClass = cm.getClass();
        Class[] argClasses = null;
        Object[] argObject = null;
        Boolean isOpen = false;
        try {

            Method method = cmClass.getMethod(getMobileDataEnabled, argClasses);

            isOpen = (Boolean) method.invoke(cm, argObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isOpen;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //apply profile one
    private void ProfileOneApply() {

        detailOneFragment = new ProfileDetailFragment();
        //
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, preferences.getInt("KEY_USER_VOLUME", detailOneFragment.default_volume), 0);

        audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, preferences.getBoolean("KEY_USER_MUTE", 0 == detailOneFragment.default_volume));

        wifiManager.setWifiEnabled(preferences.getBoolean("KEY_USER_WIFI", detailOneFragment.default_wifi));

        //ring mode
        switch (preferences.getInt("KEY_USER_RING_MODE", detailOneFragment.default_ringer_mode)) {
            case RINGER_MODE_NORMAL:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                break;
            case RINGER_MODE_SILENT:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                break;
            case RINGER_MODE_VIBRATE:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                break;
            default:
                break;
        }

        //        //控制蓝牙的开关
        if (preferences.getBoolean("KEY_USER_BLUETOOTH", detailOneFragment.default_bluetooth)) {
            bluetoothAdapter.enable();
        } else {
            bluetoothAdapter.disable();
        }

        //APN
        setMobileDataStatus(getActivity(), preferences.getBoolean("KEY_USER_GPRS", detailOneFragment.default_gprs));

        try {
            //GPS
            intent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            intent.addCategory("android.intent.category.ALTERNATIVE");
            intent.setData(Uri.parse("custom:3"));
            getActivity().getApplicationContext().sendBroadcast(intent);
        } catch (Exception e) {
            Log.d("err", "err state");
            e.printStackTrace();
        }


        //test brightness OK
        try {
                    /*
                     * 获得当前屏幕亮度的模式
                     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
                     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
                     * 屏幕亮度值 0--255
                     */
//            // 获得当前屏幕亮度值 0--255
//            int screenBrightness = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);

            // 设置屏幕亮度值为最大值255
            Window mWindow = getActivity().getWindow();
            WindowManager.LayoutParams mParams = mWindow.getAttributes();
            int userSetBrightness = preferences.getInt("KEY_USER_BRIGHTNESS", detailOneFragment.user_brightness);
            mParams.screenBrightness = userSetBrightness / (float) detailOneFragment.brightnessMax;
            mWindow.setAttributes(mParams);
            // 保存设置的屏幕亮度值
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, userSetBrightness);

            if (preferences.getBoolean("KEY_USER_BRIGHTNESS_AUTO", detailOneFragment.default_brightness_auto)) {
                Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //调试结果：滑屏至第三屏时退出
//    @Override
//    public void onDestroyView() {
//        FragmentManager mFragmentMgr = getFragmentManager();
//        FragmentTransaction mTransaction = mFragmentMgr.beginTransaction();
//        Fragment childFragment = mFragmentMgr.findFragmentByTag("TO_BACK_PRESS_FRAGMENT");
//        mTransaction.remove(childFragment);
//        mTransaction.commit();
//        super.onDestroyView();
//    }

//    //onBackPressed try
//    public void onBackPressed(){
//        //这里写返回逻辑，我写的是跳转到FragmentA
//        Toast.makeText(ButtonFragment.this.getActivity(),"ButFrag "+getActiveFragment(),Toast.LENGTH_SHORT).show();
////        String tag = getChildFragmentManager().getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1).getName();
////        getChildFragmentManager().findFragmentByTag(tag);
//    }

    //get active fragment var BackStack
    public Fragment getActiveFragment() {
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
        if (getChildFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }


        ProfileDetailFragment fragment1 = (ProfileDetailFragment) getChildFragmentManager().findFragmentByTag("ONE_FRAGMENT");
        if (fragment1 != null) {
            return fragment1;
        }
        return null;
//        else return ;
//        String tag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName();
//
//        return getFragmentManager().findFragmentByTag(tag);
//        return getChildFragmentManager().findFragmentByTag("TO_BACK_PRESS_FRAGMENT");
    }

    public boolean myOnKeyDown(int keyCode) {
        // TODO Auto-generated method stub
//        if (keyCode == KEYCODE_BACK) {
        Log.d("ButtonFragmet事件", "OK");
//            ToBackPressFragment tbp = (ToBackPressFragment) getChildFragmentManager().findFragmentByTag("TO_BACK_PRESS_FRAGMENT");
        if (getActiveFragment() instanceof ProfileDetailFragment) {
            Log.d("tobakcpressFragmet事件", "press");
            ProfileDetailFragment toBack = (ProfileDetailFragment) getActiveFragment();
            //handle back press to go back to parent fragment
            this.getChildFragmentManager().popBackStack();
//            String tag = getChildFragmentManager().getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1).getName();

//            ButtonFragment buttonFragment = (ButtonFragment)getChildFragmentManager().findFragmentByTag(tag);

//            getChildFragmentManager().beginTransaction().replace(R.id.back_press_fragment, buttonFragment).commit();
            detailOneFragment.onBackPressed();
//            }
        } else if (null == getActiveFragment()) {
            //Button 事件
//            Log.d("button 事件", "button");
            Log.d("on display buttonFragment", "hi Button");
        }
        return false;
    }

}