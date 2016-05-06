package com.gank.girl.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.gank.GankApplication;
import com.gank.R;
import com.gank.common.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureActivity extends AppCompatActivity {
    @Bind(R.id.img_girl)
    KenBurnsView imgGirl;
    private String imgurl;
    public static final String IMG_URL = "img_url";
    public static final String TRANSIT_PIC = "picture";

    public static void start(Context context, String url) {
        Intent intent = new Intent(GankApplication.getAppContext(), PictureActivity.class);
        intent.putExtra(IMG_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //StatusBarUtil.setColor(this,R.color.colorPrimary);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        imgGirl.pause();
        imgurl = getIntent().getStringExtra(IMG_URL);
        ViewCompat.setTransitionName(imgGirl, TRANSIT_PIC);
        ImageLoader.getInstance().LoadImageWithDiskCacheStrategyALL(imgurl,this).centerCrop().into(imgGirl);
        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //imgGirl.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //imgGirl.pause();


    }


}
