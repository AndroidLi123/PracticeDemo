package com.practice.todaynews.presenter;

import android.util.Log;

import com.practice.data.TodayNews;
import com.practice.todaynews.model.TodayModel;
import com.practice.todaynews.view.TodayView;

import rx.Subscriber;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class TodayPresenterImp implements TodayPresenter {
    private TodayModel todayModel;
    private TodayView todayView;

    public TodayPresenterImp(TodayModel todayModel,TodayView todayView) {
        this.todayModel = todayModel;
        this.todayView = todayView;

    }

    @Override
    public void loadData() {
        todayView.showProgress();
        todayModel.loadData().subscribe(new Subscriber<TodayNews>() {
            @Override
            public void onCompleted() {
                todayView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("error",e.getMessage());
                todayView.hideProgress();
            }

            @Override
            public void onNext(TodayNews todayNews) {
                Log.d("onnext", todayNews.getmDate());
                /*realm.beginTransaction();
                TodayNews realmRepos = realm.copyToRealmOrUpdate(todayNews);
                realm.commitTransaction();*/
                todayView.setupDayGankDataToView(todayNews);

            }
        });
    }

    @Override
    public void like() {

    }
}
