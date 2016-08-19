package com.libilibi.base;

import android.os.Bundle;

import com.libilibi.R;
import com.libilibi.common.AppConfig;
import com.libilibi.utils.StatusBarCompat;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 月海 on 2016/8/18.
 */

public abstract class RxAppCompatBaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppConfig.getAppThemeType() == AppConfig.APP_HTEME_PINK) {
            setTheme(R.style.AppBaseTheme_PINK);
        } else if (AppConfig.getAppThemeType() == AppConfig.APP_HTEME_DARK) {
            setTheme(R.style.AppBaseTheme_DARK);
        } else {
            setTheme(R.style.AppBaseTheme_PINK);
        }
        //设置布局内容
        setContentView(getLayoutId());
        //黄油刀绑定View
        ButterKnife.bind(this);
        //适配低版本，设置状态栏颜色
        StatusBarCompat.compat(this);
        //初始化Toolbar
        initToolbar();
        //初始化控件
        initViews(savedInstanceState);
    }

    protected abstract void initToolbar();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract int getLayoutId();
}
