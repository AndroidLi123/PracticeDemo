package com.gank.base;

import rx.Observable;

/**
 * Created by thinkpad on 2016/4/29.
 */
public interface BaseModel<T> {

    Observable<T> loadData();

}
