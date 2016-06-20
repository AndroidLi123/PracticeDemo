package com.practice.network;

import android.content.Context;

import com.practice.common.CommonUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiXiaoWang
 */
public class CacheControlInterceptor implements Interceptor {
    private Context context;

    public CacheControlInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean isResponseFromCache = false;
        Request request = chain.request();
        if (!CommonUtils.isNetWorkConnected(context)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            isResponseFromCache = true;
        }
        Response originalResponse = chain.proceed(request);
        if (isResponseFromCache)
            return originalResponse;
        if (CommonUtils.isNetWorkConnected(context)) {
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", cacheControl)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
            return originalResponse.newBuilder().removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}

