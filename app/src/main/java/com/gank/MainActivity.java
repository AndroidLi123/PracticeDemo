package com.gank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.gank.base.BaseActivity;
import com.gank.base.BaseNewTaskFragmentPagerAdapter;
import com.gank.girl.view.GirlFragment;
import com.gank.setting.SettingFragment;
import com.gank.todaynews.view.TodayFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab)
    TabLayout tabLayout;
    private BaseNewTaskFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        viewPager.setOffscreenPageLimit(3);
        adapter = new BaseNewTaskFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayFragment());
        adapter.addFragment(new GirlFragment());
        adapter.addFragment(new SettingFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.removeAllTabs();
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),R.drawable.selector_hot,"热文");
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),R.drawable.selector_girl,"美图");
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),R.drawable.selector_user,"我的");
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(0)) {
                    viewPager.setCurrentItem(0, false);
                } else if (tab == tabLayout.getTabAt(1)) {
                    viewPager.setCurrentItem(1, false);
                } else if (tab == tabLayout.getTabAt(2)) {
                    viewPager.setCurrentItem(2, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void addTab(View view, int drawable, String title) {
        view.findViewById(R.id.img_tab).setBackgroundResource(drawable);
        TextView textView1 = ((TextView) view.findViewById(R.id.txt_tab));
        textView1.setText(title);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

}
