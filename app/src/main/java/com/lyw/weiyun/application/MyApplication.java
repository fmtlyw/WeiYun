package com.lyw.weiyun.application;

import android.app.Application;
import com.chenenyu.router.Configuration;
import com.chenenyu.router.Router;

import cn.bmob.v3.Bmob;

/**
 * 功能描述:
 * Created on 2020/6/29.
 * @author lyw
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        initRouter();
        initBomb();
    }

    /**
     * 初始化Bomb
     */
    private void initBomb() {
        Bmob.initialize(this, "97943ba45a9cc545a484fa982fa5179d");
    }

    /**
     * 初始化路由
     */
    private void initRouter() {
        Configuration.Builder builder = new Configuration.Builder();
        builder.registerModules("app", "modulemain", "modulelogin");
        Router.initialize(builder.build());
    }
}
