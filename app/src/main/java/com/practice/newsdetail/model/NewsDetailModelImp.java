package com.practice.newsdetail.model;

import android.content.Context;

import com.practice.network.RetrofitNetwork;
import com.practice.data.News;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiXiaoWang
 */
public class NewsDetailModelImp implements NewsDetailModel {
    private Context context;

    public NewsDetailModelImp(Context context) {
        this.context = context;
    }

    @Override
    public Observable<News> laodNewsDetail(long id) {
        return new RetrofitNetwork().getGankAPIService().
                getNews(id).subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));
    }
}
