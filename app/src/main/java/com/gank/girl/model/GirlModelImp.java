package com.gank.girl.model;

import com.gank.data.GankData;
import com.gank.network.RetrofitNetwork;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LiXiaoWang
 */
public class GirlModelImp implements GirlModel{
    @Override
    public Observable<GankData> loadGirls() {
        return new RetrofitNetwork().getGankAPIService().
                getGrils().subscribeOn(Schedulers.io()).
                observeOn((AndroidSchedulers.mainThread()));
    }
}
