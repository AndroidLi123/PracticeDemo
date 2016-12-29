package com.practice.module.girl.view;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.practice.R;
import com.practice.common.base.BaseActivity;
import com.practice.common.util.CommonUtils;
import com.practice.common.util.ImageLoader;
import com.practice.common.util.ImageLoaderUtil;

import java.io.IOException;

import butterknife.Bind;
import butterknife.OnLongClick;

public class PictureActivity extends BaseActivity {
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
    protected int setLayoutId() {
        return R.layout.activity_picture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        imgurl = getIntent().getStringExtra(IMG_URL);
        ImageLoaderUtil.getInstance().loadImage(this, createImageLoader());
        onImgGirlClick();

    }

    private void onImgGirlClick() {
        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @NonNull
    private ImageLoader createImageLoader() {
        ImageLoader.Builder builder = new ImageLoader.Builder();
        builder.imgView(imgGirl);
        builder.url(imgurl);
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder.build();
    }


    @OnLongClick(R.id.img_girl)
    public boolean downloadOrWallpapger() {
        createDialog();
        return true;

    }

    private void createDialog() {
        new AlertDialog.Builder(this).setItems(
                new String[]{getString(R.string.download), getString(R.string.setwallpaper)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                download();
                                break;
                            case 1:
                                setWallPager();
                                break;

                        }

                    }
                }).show();

    }

    private void download() {
        Bitmap bitmap = null;
        ImageLoaderUtil.getInstance().saveImage(this, createImageLoader(), bitmap);

    }

    private void setWallPager() {
        final WallpaperManager myWallpaperManager
                = WallpaperManager.getInstance(getApplicationContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myWallpaperManager.setBitmap(CommonUtils.getBitMap(imgurl));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
