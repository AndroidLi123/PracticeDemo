package com.practice.common.util;

import android.content.Context;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseImageLoaderProvider {
    public abstract Target<GlideDrawable> loadImage(Context ctx, ImageLoader img);
}
