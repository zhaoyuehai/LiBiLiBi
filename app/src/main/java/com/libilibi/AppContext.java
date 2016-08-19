package com.libilibi;

import com.libilibi.base.BaseApplication;

/**
 * Created by 月海 on 2016/8/18.
 */

public class AppContext extends BaseApplication {

    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
        //网络请求初始化， 部分类库初始化
        //Log日志打印控制
        //自动登录相关
    }

    public static AppContext getInstance() {
        return instance;
    }
}
