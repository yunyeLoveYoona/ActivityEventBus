package com.test.activityeventbus;

import android.app.Application;

/**
 * Created by dell on 2018/4/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(ActivityEventBus.getInstance());
    }
}
