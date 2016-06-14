package com.practice.newsdetail.view;

import com.practice.base.BaseView;
import com.practice.data.News;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailView extends BaseView{
    void loadNewsDetail();
    void setupDataToView(News news);

}
