package com.practice.module.newsdetail.model;

import android.content.Context;

import com.practice.common.network.RetrofitNetwork;
import com.practice.common.data.News;

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
        return RetrofitNetwork.getInstance().getGankAPIService().
                getNews(id).subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));
    }
}
