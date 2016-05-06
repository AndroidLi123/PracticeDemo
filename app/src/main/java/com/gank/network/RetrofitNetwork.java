package com.gank.network;

import com.gank.GankApplication;
import com.gank.api.GankAPIService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by thinkpad on 2016/4/29.
 */
public class RetrofitNetwork {

    private final GankAPIService gankAPIService;
    private final Retrofit retrofit;
    // private static RetrofitNetwork retrofitNetwork = new RetrofitNetwork();
   /* public static RetrofitNetwork getInstance() {
        return retrofitNetwork;
    }*/

    public RetrofitNetwork() {
        File httpCacheDirectory = new File(GankApplication.sContext.getCacheDir(), "responses");
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        CacheControlInterceptor cacheControlInterceptor = new CacheControlInterceptor(GankApplication.sContext);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).
                addInterceptor(loggingInterceptor).cache(cache).
                addInterceptor((cacheControlInterceptor)).addNetworkInterceptor(cacheControlInterceptor).
                build();

        retrofit = new Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/").
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                client(okHttpClient).
                build();

        gankAPIService = retrofit.create(GankAPIService.class);

    }

    public GankAPIService getGankAPIService() {
        return gankAPIService;
    }
}
