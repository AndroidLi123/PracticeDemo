package com.gank.newsdetail.view;

import com.gank.base.BaseView;
import com.gank.data.News;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailView extends BaseView{
    void loadNewsDetail();
    void setupDataToView(News news);

}
