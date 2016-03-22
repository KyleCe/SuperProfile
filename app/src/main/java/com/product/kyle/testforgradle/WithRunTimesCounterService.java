package com.product.kyle.testforgradle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WithRunTimesCounterService extends Service {
    public WithRunTimesCounterService() {
    }

    int counter = 0;

    int[] wallpapers = new int[]{
            R.drawable.flux2,
            R.drawable.smart_life_circle,
            R.drawable.timeout
    };
//    WallpaperManager wManager;
    int current = 0;


    @Override
    public void onCreate() {
        super.onCreate();
//        wManager = WallpaperManager.getInstance(this);
        Log.d("onCreate()", "service");
        counter++;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (current >= 3)
            current = 0;
        try {
//            wManager.setResource(wallpapers[current++]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("onStartCommand()", "service");
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
        Log.d("stopService()", "service");
        return super.stopService(name);

    }

    //t 为计数器归零
    public int getRunTimesOrReset(int t) {
        return t == 0 ? t : counter;
    }

}
