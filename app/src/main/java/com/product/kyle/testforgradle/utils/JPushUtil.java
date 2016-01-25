package com.product.kyle.testforgradle.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by kyle on 24/4/15.
 */

public class JPushUtil {
    public static final String PREFS_NAME = "JPUSH_EXAMPLE";
    public static final String PREFS_DAYS = "JPUSH_EXAMPLE_DAYS";
    public static final String PREFS_START_TIME = "PREFS_START_TIME";
    public static final String PREFS_END_TIME = "PREFS_END_TIME";
    public static final String KEY_APP_KEY = "JPUSH_APPKEY";

    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }


    public static void setPushTag(final Context context, final String alias) {
        JPushInterface.setAliasAndTags(context,
                alias, null, new TagAliasCallback() {

                    Handler mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            setPushTag(context, alias);

                        }
                    };

                    @Override
                    public void gotResult(int code, String alias, Set<String> tags) {

                        if (code == 6002) {
                            if (isNetCanUse()) {
                                mHandler.sendEmptyMessageAtTime(0, 60 * 1000);
                            }
                        }

                    }

                });

    }

    //// from SOF
    static Context mContext;
    public JPushUtil(Context mContext){
        this.mContext = mContext;
    }

    public static boolean isNetCanUse() {
        try {
            ConnectivityManager connectivity =
                    (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }


//    // 校验Tag Alias 只能是数字,英文字母和中文
//    public static boolean isValidTagAndAlias(String s) {
//        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
//        Matcher m = p.matcher(s);
//        return m.matches();
//    }
//
//    // 取得AppKey
//    public static String getAppKey(Context context) {
//        Bundle metaData = null;
//        String appKey = null;
//        try {
//            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
//                    context.getPackageName(), PackageManager.GET_META_DATA);
//            if (null != ai)
//                metaData = ai.metaData;
//            if (null != metaData) {
//                appKey = metaData.getString(KEY_APP_KEY);
//                if ((null == appKey) || appKey.length() != 24) {
//                    appKey = null;
//                }
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        }
//        return appKey;
//    }
//
//    // 取得版本号
//    public static String GetVersion(Context context) {
//        try {
//            PackageInfo manager = context.getPackageManager().getPackageInfo(
//                    context.getPackageName(), 0);
//            return manager.versionName;
//        } catch (PackageManager.NameNotFoundException e) {
//            return "Unknown";
//        }
//    }
//
//    public static void showToast(final String toast, final Context context)
//    {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        }).start();
//    }
//
//    public static boolean isConnected(Context context) {
//        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = conn.getActiveNetworkInfo();
//        return (info != null && info.isConnected());
//    }
//
//    public static String getImei(Context context, String imei) {
//        try {
//            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            imei = telephonyManager.getDeviceId();
//        } catch (Exception e) {
//            Log.e(JpushUtil.class.getSimpleName(), e.getMessage());
//        }
//        return imei;
//    }
}
