package com.practice.girl.model;

import com.practice.data.GankData;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface GirlModel {
    Observable<GankData> loadGirls();
}
