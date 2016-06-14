package com.practice.girl.presenter;

import com.practice.data.GankData;
import com.practice.girl.model.GirlModel;
import com.practice.girl.view.GirlView;

import rx.Subscriber;

/**
 * Created by LiXiaoWang
 */
public class GirlPresenterImp implements GirlPresenter{
    private GirlModel girlModel;
    private GirlView girlView;
    public GirlPresenterImp(GirlModel girlModel,GirlView girlView) {
        this.girlModel = girlModel;
        this.girlView = girlView;
    }

    @Override
    public void loadGirls() {
        girlView.showProgress();
        girlModel.loadGirls().subscribe(new Subscriber<GankData>() {
            @Override
            public void onCompleted() {
                girlView.hideProgress();

            }

            @Override
            public void onError(Throwable e) {
                girlView.hideProgress();

            }

            @Override
            public void onNext(GankData gankData) {
                girlView.setupDataToView(gankData);

            }
        });

    }
}
