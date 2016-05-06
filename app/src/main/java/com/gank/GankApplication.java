package com.gank;

import android.app.Application;
import android.content.Context;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class GankApplication extends Application{
    public static Context sContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
      
    }

    public static Context getAppContext() {
        return sContext;
    }
}
