package com.practice.module.girl.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.practice.R;
import com.practice.common.base.BaseActivity;
import com.practice.common.util.ImageLoader;
import com.practice.common.util.ImageLoaderUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureActivity extends BaseActivity{
    @Bind(R.id.img_girl)
    KenBurnsView imgGirl;
    private String imgurl;
    public static final String IMG_URL = "img_url";
    public static final String TRANSIT_PIC = "picture";

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(IMG_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        imgurl = getIntent().getStringExtra(IMG_URL);
        ImageLoaderUtil.getInstance().loadImage(this,createBuilder().build());
        onImgGirlClick();

    }

    private void onImgGirlClick() {
        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @NonNull
    private ImageLoader.Builder createBuilder() {
        ImageLoader.Builder builder = new ImageLoader.Builder();
        builder.imgView(imgGirl);
        builder.url(imgurl);
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder;
    }

}
