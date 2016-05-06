package com.gank.newsdetail.model;

import com.gank.data.News;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailModel {
    Observable<News> laodNewsDetail(long id);
}
