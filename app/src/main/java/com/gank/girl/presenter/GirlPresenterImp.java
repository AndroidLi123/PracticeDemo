package com.gank.girl.presenter;

import com.gank.data.GankData;
import com.gank.girl.model.GirlModel;
import com.gank.girl.view.GirlView;

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
