package com.gank.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

/**
 * Created by LiXiaoWang
 */
public class ImageLoader {

    public static ImageLoader getInstance() {
        return new ImageLoader();
    }


    public Target<GlideDrawable> LoadImage(String url, Context context, ImageView imageView) {
        return Glide.with(context).load(url).into(imageView);
    }

    public DrawableRequestBuilder<String> LoadImageWithDiskCacheStrategyALL(String url, Context context) {
        return Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}
