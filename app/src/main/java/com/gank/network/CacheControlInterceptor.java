package com.gank.network;

import android.content.Context;

import com.gank.common.CommonUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by thinkpad on 2016/4/5.
 */
public class CacheControlInterceptor implements Interceptor {
    private Context context;

    public CacheControlInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (!CommonUtils.isNetWorkConnected(context)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (CommonUtils.isNetWorkConnected(context)) {
            int maxAge = 60; // 在线缓存在1分钟内可读取
            String cachecontrol = request.cacheControl().toString();
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
