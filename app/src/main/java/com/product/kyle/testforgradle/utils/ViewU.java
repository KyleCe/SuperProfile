package com.product.kyle.testforgradle.utils;

import android.view.View;

/**
 * Created by Administrator on 2015/10/15.
 */
public class ViewU {
    public static void hide(View... views) {
        for (View v : views) if (v != null) v.setVisibility(View.GONE);
    }

    public static void show(View... views) {
        for (View v : views) if (v != null) v.setVisibility(View.VISIBLE);
    }

    public static void showAndHide(View sv, View hv) {
        show(sv);
        hide(hv);
    }

    public static void hideAndShow(View hv, View sv) {
        showAndHide(sv, hv);
    }

    public static void enClick(View... views) {
        for (View v : views) if (v != null) v.setClickable(true);
    }

    public static void disClick(View... views) {
        for (View v : views) if (v != null) v.setClickable(false);
    }

    public static void clearClickListener(View... views) {
        for (View v : views) if (v != null) v.setOnClickListener(null);
    }


    public static void setClickListener(View.OnClickListener listener, View... views) {
        for (View v : views) if (v != null) v.setOnClickListener(listener);
    }


}
