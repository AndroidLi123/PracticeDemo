package com.practice.common.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

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
        mProvider = new GlideImageLoaderProvider();
    }

    public Target<GlideDrawable> loadImage(Context context, ImageLoader img) {
        return mProvider.loadImage(context, img);
    }

    public void saveImage(Context context, ImageLoader img, Bitmap bitmap) {
        mProvider.saveImage(context,img,bitmap);
    }
}
