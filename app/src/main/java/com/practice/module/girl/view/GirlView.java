package com.practice.module.girl.view;

import com.practice.common.data.GankData;

/**
 * Created by LiXiaoWang
 */
public interface GirlView {
    void setupDataToView(GankData gankData);
    void showProgress();
    void hideProgress();
}
