package com.gank.girl.view;

import com.gank.data.GankData;

/**
 * Created by LiXiaoWang
 */
public interface GirlView {
    void setupDataToView(GankData gankData);
    void showProgress();
    void hideProgress();
}
