package com.libilibi.common;

import android.os.Environment;

import com.libilibi.AppContext;

/**
 * Created by 月海 on 2016/8/18.
 */

public class AppConfig {
    public static final String CACHE_BASE_PATH = Environment.getExternalStorageDirectory() + "/libilibi/cache";
    public static final String SP_THEME_TYPE = "pref_theme_type";
    public static final int APP_HTEME_PINK = 1;
    public static final int APP_HTEME_DARK = 0;

    public static int getAppThemeType() {
        return AppContext.get(SP_THEME_TYPE, 0);
    }

    public static void setAppThemeType(int themeType) {
        AppContext.set(SP_THEME_TYPE, themeType);
    }
}
