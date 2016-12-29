package com.practice.module.todaynews.presenter;

import com.practice.common.data.TodayNews;
import com.practice.module.todaynews.model.TodayModel;
import com.practice.module.todaynews.view.TodayView;

import rx.Subscriber;

/**
 * Created by LiXiaoWang
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
                todayView.hideProgress();
            }

            @Override
            public void onNext(TodayNews todayNews) {
                todayView.setupDayGankDataToView(todayNews);

            }
        });
    }

    @Override
    public void like() {

    }
}
