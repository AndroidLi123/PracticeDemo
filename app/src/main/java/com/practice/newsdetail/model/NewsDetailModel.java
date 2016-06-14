package com.practice.newsdetail.model;

import com.practice.data.News;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailModel {
    Observable<News> laodNewsDetail(long id);
}
