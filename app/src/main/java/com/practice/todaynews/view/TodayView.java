package com.practice.todaynews.view;

import com.practice.base.BaseView;
import com.practice.data.TodayNews;

/**
 * Created by thinkpad on 2016/4/29.
 */
public interface TodayView extends BaseView{
    void setupDayGankDataToView(TodayNews todayNews);
}
