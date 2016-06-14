package com.practice.network;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.practice.PracticeApplication;
import com.practice.api.GankAPIService;
import com.practice.data.StringObject;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.realm.RealmList;
import io.realm.RealmObject;
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
    public RetrofitNetwork() {
        File httpCacheDirectory = new File(PracticeApplication.sContext.getCacheDir(), "responses");
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        CacheControlInterceptor cacheControlInterceptor = new CacheControlInterceptor(PracticeApplication.sContext);
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).registerTypeAdapter(new TypeToken<RealmList<StringObject>>() {
                }.getType(), new TypeAdapter<RealmList<StringObject>>() {

                    @Override
                    public void write(JsonWriter out, RealmList<StringObject> value) throws IOException {
                        // Ignore
                    }

                    @Override
                    public RealmList<StringObject> read(JsonReader in) throws IOException {
                        RealmList<StringObject> list = new RealmList<StringObject>();
                        in.beginArray();
                        while (in.hasNext()) {
                            list.add(new StringObject(in.nextString()));
                        }
                        in.endArray();
                        return list;
                    }
                })
                .create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).
                addInterceptor(loggingInterceptor).cache(cache).
                addInterceptor((cacheControlInterceptor)).addNetworkInterceptor(cacheControlInterceptor).
                build();

        retrofit = new Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/").
                addConverterFactory(GsonConverterFactory.create(gson)).
                addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
                client(okHttpClient).
                build();

        gankAPIService = retrofit.create(GankAPIService.class);

    }

    public GankAPIService getGankAPIService() {
        return gankAPIService;
    }
}
