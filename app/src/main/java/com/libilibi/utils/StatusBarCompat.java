package com.libilibi.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 月海 on 2016/8/18.
 */

public class StatusBarCompat {
    private static final int INVALID_VALUES = -1;//无效值
    private static final int COLOR_DEFAULT = Color.parseColor("#FA7198");//无效值

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, int statusColor) {
        //大于等于android 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusColor != INVALID_VALUES) {
                activity.getWindow().setStatusBarColor(statusColor);
            }
            return;
        }
        //大于等于android 4.4.4 小于android 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int color = COLOR_DEFAULT;
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VALUES) {
                color = statusColor;
            }
            View statusBarView = contentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getMeasuredHeight() == getStatusBarHeight(activity)) {
                statusBarView.setBackgroundColor(color);
                //若存在状态栏，则修改颜色后返回，防止重复添加
                return;
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, layoutParams);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void compat(Activity activity) {
        compat(activity, INVALID_VALUES);
    }
}
