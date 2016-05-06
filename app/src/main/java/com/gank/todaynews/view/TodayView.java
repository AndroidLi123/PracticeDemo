package com.gank.todaynews.view;

import com.gank.base.BaseView;
import com.gank.data.TodayNews;

/**
 * Created by thinkpad on 2016/4/29.
 */
public interface TodayView extends BaseView{
    void setupDayGankDataToView(TodayNews todayNews);
}
