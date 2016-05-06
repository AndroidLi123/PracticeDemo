package com.gank.todaynews.model;

import android.content.Context;

import com.gank.data.TodayNews;
import com.gank.network.RetrofitNetwork;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class TodayModelImp implements TodayModel {
    private Context context;

    public TodayModelImp(Context context) {
        this.context = context;
    }

    @Override
    public Observable<TodayNews> loadData() {
        return new RetrofitNetwork().getGankAPIService().
                getTodayNews().subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));
    }
}

