package com.practice;

import android.app.Application;
import android.content.Context;

import com.zhy.changeskin.SkinManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by LiXiaoWang
 */
public class PracticeApplication extends Application{
    public static Context sContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        SkinManager.getInstance().init(this);
    }

    public static Context getAppContext() {
        return sContext;
    }
}
