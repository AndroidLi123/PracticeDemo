package com.practice.common.util;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.practice.R;

/**
 * Created by LiXiaoWang
 */
public class ImageLoader {
    private String url;
    private int placeHolder;
    private ImageView imgView;
    private DiskCacheStrategy diskCacheStrategy;

    private ImageLoader(Builder builder) {
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imgView = builder.imgView;
        this.diskCacheStrategy = builder.diskCacheStrategy;
    }


    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImgView() {
        return imgView;
    }


    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public static class Builder {
        private String url;
        private int placeHolder;
        private ImageView imgView;
        private DiskCacheStrategy diskCacheStrategy;

        public Builder() {
            this.url = "";
            this.placeHolder = R.color.colorPrimary;
            this.imgView = null;
            this.diskCacheStrategy = DiskCacheStrategy.ALL;
        }


        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }


        public Builder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }
}
