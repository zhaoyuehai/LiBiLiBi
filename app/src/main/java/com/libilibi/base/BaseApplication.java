package com.libilibi.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.libilibi.R;

/**
 * Created by 月海 on 2016/8/18.
 *
 *  提供了获取 Context,Resources,String方法；
 *  提供了设置和获取 各种类型值的 SharedPreferences 方法；
 *  提供了 弹吐司的方法，包含长弹和短弹，带有自定义图标，自定义位置的吐司。
 */

public class BaseApplication extends Application {

    private static Context mContext;
    private static Resources mResource;
    private static String mLastToast;
    private static final String SP_NAME = "libilibi_pref";
    private static long mLastToastTime;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mResource = getResources();
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) mContext;
    }

    public static synchronized Resources resource() {
        return mResource;
    }

    public static synchronized String string(int resId) {
        return mResource.getString(resId);
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static int get(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    public static boolean get(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    private static void apply(SharedPreferences.Editor editor) {
        //版本大于 9 'GINGERBREAD'，用apply() ; 小宇 9 'GINGERBREAD' ,用commit();
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences() {
        return context().getSharedPreferences(SP_NAME, Context.MODE_MULTI_PROCESS);
    }

    public static void showToast(int message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int icon) {
        showToast(message, Toast.LENGTH_LONG, icon, Gravity.BOTTOM);
    }

    public static void showToastShort(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showToastShort(int message, int icon) {
        showToast(message, Toast.LENGTH_SHORT, icon, Gravity.BOTTOM);
    }

    public static void showToast(int message, int duration, int icon, int gravity) {
        showToast(context().getString(message), duration, icon, gravity);
    }

    public static void showToast(String message, int duration, int icon, int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(mLastToast) || Math.abs(time - mLastToastTime) > 2000) {
                View view = LayoutInflater.from(context()).inflate(R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.tv_toast)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_toast)).setImageResource(icon);
                    (view.findViewById(R.id.icon_toast)).setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(context());
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }
                toast.setDuration(duration);
                toast.show();
                mLastToast = message;
                mLastToastTime = System.currentTimeMillis();
            }
        }
    }
}
