package com.product.kyle.testforgradle;

import android.app.Service;
import android.app.WallpaperManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplyProfileService extends Service {



    //shared preference
    SharedPreferences preferences;

    final static int RINGER_MODE_NORMAL = 1;
    final static int RINGER_MODE_SILENT = 2;
    final static int RINGER_MODE_VIBRATE = 3;

    final static int NOT_SET = -1;

    private AudioManager audioManager = null;
    BluetoothAdapter bluetoothAdapter = null;

    WifiManager wifiManager = null;//Wifi

    public ApplyProfileService() {
    }


    int[] wallpapers = new int[]{
            R.drawable.flux2,
            R.drawable.smart_life_circle,
            R.drawable.timeout
    };
    WallpaperManager wManager;
    int current = 0;


    @Override
    public void onCreate()
    {
        super.onCreate();
        wManager = WallpaperManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
//        if (current >= 3)
//            current = 0;
//        try{
//            wManager.setResource(wallpapers[current++]);
//        }catch(Exception e){
//            e.printStackTrace();
//        }




        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean stopService(Intent name) {
        // TODO Auto-generated method stub
        super.stopSelf();
        return super.stopService(name);

    }



    /*
    * APN reflect
     * 移动数据开启和关闭  测试通过
    * */
    public void setMobileDataStatus(Context context, boolean enabled) {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

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
}
