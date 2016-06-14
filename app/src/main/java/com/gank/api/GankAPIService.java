package com.gank.api;

import com.gank.data.DayGankData;
import com.gank.data.GankData;
import com.gank.data.HistoryData;
import com.gank.data.News;
import com.gank.data.TodayNews;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by thinkpad on 2016/4/29.
 */
public interface GankAPIService {
    @GET("day/history")
    Observable<HistoryData> getHistoryData();

    @GET("day/2016/04/29")
    Observable<DayGankData>getDayData();

    @Headers("Cache-Control: public, max-age=10")
    @GET("news/latest")
    Observable<TodayNews> getTodayNews();

    @GET("news/{newsId}")
    Observable<News> getNews(@Path("newsId") long newsId);

    @GET("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<GankData>getGrils();
}
