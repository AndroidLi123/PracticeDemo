package com.practice.module.newsdetail.model;

import com.practice.common.data.News;

import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailModel {
    Observable<News> laodNewsDetail(long id);
}
