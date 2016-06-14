package com.practice.girl.view;

import com.practice.data.GankData;

/**
 * Created by LiXiaoWang
 */
public interface GirlView {
    void setupDataToView(GankData gankData);
    void showProgress();
    void hideProgress();
}
