package com.practice.common.util;

import android.content.Context;

/**
 * Created by LiXiaoWang
 */
public class ImageLoaderUtil {
    public static final int PIC_LARGE = 0;
    public static final int PIC_MEDIUM = 1;
    public static final int PIC_SMALL = 2;

    public static final int LOAD_STRATEGY_NORMAL = 0;
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;

    private static ImageLoaderUtil mInstance;
    private BaseImageLoaderProvider mProvider;
    private static ImageLoaderUtil ourInstance = new ImageLoaderUtil();

    public static ImageLoaderUtil getInstance() {
        return ourInstance;
    }

    private ImageLoaderUtil() {
        mProvider =new GlideImageLoaderProvider();
    }

    public void loadImage(Context context,MyImageLoader img){
        mProvider.loadImage(context,img);
    }

}
