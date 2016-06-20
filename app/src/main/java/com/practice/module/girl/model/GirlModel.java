package com.practice.module.girl.model;

import com.practice.common.data.GankData;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface GirlModel {
    Observable<GankData> loadGirls();
}
