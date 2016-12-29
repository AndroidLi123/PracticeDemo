package com.practice.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.changeskin.SkinManager;

import butterknife.ButterKnife;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int setLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        SkinManager.getInstance().register(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        SkinManager.getInstance().unregister(this);
    }

}
