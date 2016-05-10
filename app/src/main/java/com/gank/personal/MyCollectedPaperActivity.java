package com.gank.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.gank.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyCollectedPaperActivity extends AppCompatActivity {

    @Bind(R.id.container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collected_paper);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyCollectedPaperListFragment()).commit();
    }
}
