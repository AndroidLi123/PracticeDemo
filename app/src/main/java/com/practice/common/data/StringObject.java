package com.practice.common.data;

import io.realm.RealmObject;

/**
 * Created by LiXiaoWang
 */
public class StringObject extends RealmObject{

    private String string;

    public StringObject() {
    }

    public StringObject(String s) {
        this.string =s;

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
