package com.practice.common.util;

import android.content.Context;

/**
 * Created by LiXiaoWang
 */
public class ImageLoaderUtil {
    private BaseImageLoaderProvider mProvider;
    private static ImageLoaderUtil ourInstance = new ImageLoaderUtil();

    public static ImageLoaderUtil getInstance() {
        return ourInstance;
    }

    private ImageLoaderUtil() {
        mProvider =new GlideImageLoaderProvider();
    }

    public void loadImage(Context context,ImageLoader img){
        mProvider.loadImage(context,img);
    }

}
