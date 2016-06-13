package com.gank.common;

import android.widget.ImageView;

import com.gank.R;

/**
 * Created by LiXiaoWang
 */
public class MyImageLoader {
    private int type;  //类型 (大图，中图，小图)
    private String url; //需要解析的url
    private int placeHolder; //当没有成功加载的时候显示的图片
    private ImageView imgView; //ImageView的实例
    private int strategy;//加载策略，是否在wifi下才加载

    private MyImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imgView = builder.imgView;
        this.strategy = builder.strategy;
    }
    public int getType() {
        return type;
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

    public int getStrategy() {
        return strategy;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private ImageView imgView;
        private int strategy;

        public Builder() {
            this.type = ImageLoaderUtil.PIC_SMALL;
            this.url = "";
            this.placeHolder = R.color.colorPrimary;
            this.imgView = null;
            this.strategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
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

        public Builder strategy(int strategy) {
            this.strategy = strategy;
            return this;
        }

        public MyImageLoader build() {
            return new MyImageLoader(this);
        }

    }
}
