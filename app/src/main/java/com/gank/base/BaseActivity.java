package com.gank.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.changeskin.SkinManager;

/**
 * Created by LiXiaoWang
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().register(this);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }

}
