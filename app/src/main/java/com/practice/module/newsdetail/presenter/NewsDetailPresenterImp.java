package com.practice.module.newsdetail.presenter;

import android.util.Log;

import com.practice.common.data.News;
import com.practice.module.newsdetail.model.NewsDetailModelImp;
import com.practice.module.newsdetail.view.NewsDetailView;

import rx.Subscriber;

/**
 * Created by LiXiaoWang
 */
public class NewsDetailPresenterImp implements NewsDetailPresenter {
    private NewsDetailModelImp modelImp;
    private NewsDetailView newsDetailView;

    public NewsDetailPresenterImp(NewsDetailModelImp modelImp, NewsDetailView newsDetailView) {
        this.modelImp = modelImp;
        this.newsDetailView = newsDetailView;
    }

    @Override
    public void loadNewsDetail(long id) {
        newsDetailView.showProgress();
        modelImp.laodNewsDetail(id).subscribe(new Subscriber<News>() {
            @Override
            public void onCompleted() {
                newsDetailView.hideProgress();

            }

            @Override
            public void onError(Throwable e) {
                Log.d("error", e.getMessage());
                newsDetailView.hideProgress();

            }

            @Override
            public void onNext(News news) {
                newsDetailView.setupDataToView(news);
            }
        });

    }
}
