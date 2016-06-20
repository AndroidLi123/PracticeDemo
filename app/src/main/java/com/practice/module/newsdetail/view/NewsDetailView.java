package com.practice.module.newsdetail.view;

import com.practice.common.base.BaseView;
import com.practice.common.data.News;

/**
 * Created by LiXiaoWang
 */
public interface NewsDetailView extends BaseView{
    void loadNewsDetail(long newsid);
    void setupDataToView(News news);

}
