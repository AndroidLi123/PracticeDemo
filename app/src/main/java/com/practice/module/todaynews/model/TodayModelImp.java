package com.practice.module.todaynews.model;

import com.practice.common.data.TodayNews;
import com.practice.common.network.RetrofitNetwork;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class TodayModelImp implements TodayModel {
    @Override
    public Observable<TodayNews> loadData() {
        return RetrofitNetwork.getInstance().getGankAPIService().
                getTodayNews().subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));

    }
}

