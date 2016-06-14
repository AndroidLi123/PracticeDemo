package com.practice.common;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class DbHelper {
    private static DbHelper ourInstance = new DbHelper();

    public static DbHelper getInstance() {
        return ourInstance;
    }

    public static RealmQuery<? extends RealmObject> query(Class<?extends RealmObject> mClass) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(mClass);

    }

    public RealmResults<? extends RealmObject> findAll(RealmQuery<? extends RealmObject> query) {
        return query.findAll();
    }
    public static void write() {

    }
    private DbHelper() {
    }
}
