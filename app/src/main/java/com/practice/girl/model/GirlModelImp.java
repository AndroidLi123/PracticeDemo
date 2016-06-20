package com.practice.girl.model;

import com.practice.data.GankData;
import com.practice.network.RetrofitNetwork;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiXiaoWang
 */
public class GirlModelImp implements GirlModel{
    @Override
    public Observable<GankData> loadGirls() {
        return RetrofitNetwork.getInstance().getGankAPIService().
                getGrils().subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));
    }
}
