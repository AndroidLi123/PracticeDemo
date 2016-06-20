package com.practice.module.todaynews.view;

import com.practice.common.base.BaseView;
import com.practice.common.data.TodayNews;

/**
 * Created by thinkpad on 2016/4/29.
 */
public interface TodayView extends BaseView{
    void setupDayGankDataToView(TodayNews todayNews);
}
