package com.product.kyle.testforgradle.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//            ZwyLog.d("[PushReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//            ZwyLog.d("[PushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//            ZwyLog.d("[PushReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//            ZwyLog.d("[PushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            processCustomMessageReceive(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            ZwyLog.d("[PushReceiver] 用户点击打开了通知");
            processCustomMessageOnClick(context, bundle);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//            ZwyLog.d("[PushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//            ZwyLog.e("[PushReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
//            ZwyLog.d("[PushReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    /**
     * 收到通知
     */
    private void processCustomMessageReceive(Context context, Bundle bundle) {
        String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
//        ZwyLog.v("json = " + json);
//        IPushMessage pushMessage = PushMessageFactory.getPushMessage(json);
//        if (pushMessage != null) {
//            pushMessage.receive(context);
//        }

    }

    /**
     * 点击通知
     */
    private void processCustomMessageOnClick(Context context, Bundle bundle) {
        String json = bundle.getString(JPushInterface.EXTRA_EXTRA);
//        ZwyLog.v("json:" + json);
//        IPushMessage pushMessage = PushMessageFactory.getPushMessage(json);
//        if (pushMessage != null) {
//            pushMessage.onClick(context);
//        }

    }

}
