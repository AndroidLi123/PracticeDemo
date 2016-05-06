package com.gank.girl.model;

import com.gank.data.GankData;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface GirlModel {
    Observable<GankData> loadGirls();
}
