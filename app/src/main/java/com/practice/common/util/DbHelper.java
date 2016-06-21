package com.practice.common.util;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class DbHelper<T extends RealmObject> {
    private Realm realm;
    private Class<T> typeParameterClass;
    public Realm getRealm() {
        return realm;
    }

    public DbHelper(Class<T> typeParameterClass)

    {
        realm = Realm.getDefaultInstance();
        this.typeParameterClass = typeParameterClass;

    }


    public RealmQuery<T> query() {
        return realm.where(typeParameterClass);

    }

    public RealmResults<T> findAll() {
        return query().findAll();
    }

    public void remove(RealmObject realmObject) {
        realm.beginTransaction();
        realmObject.removeFromRealm();
        realm.commitTransaction();

    }

    public void copy(RealmObject realmObject) {
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealm(realmObject);
            realm.commitTransaction();
        }

    }

}
