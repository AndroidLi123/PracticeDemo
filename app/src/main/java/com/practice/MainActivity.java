package com.practice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.practice.common.base.BaseActivity;
import com.practice.common.base.BaseNewTaskFragmentPagerAdapter;
import com.practice.module.girl.view.GirlFragment;
import com.practice.module.setting.SettingFragment;
import com.practice.module.todaynews.view.TodayFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        tabLayout.setupWithViewPager(initViewPager(viewPager));
        addTabToTabLayout();
        setOnTabSelectedListener();
    }

    private ViewPager initViewPager(ViewPager viewPager) {
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(initAdapter());
        return viewPager;
    }

    private BaseNewTaskFragmentPagerAdapter initAdapter() {
        return addFragmentToAdapter(createPagerAdapter());
    }

    @NonNull
    private BaseNewTaskFragmentPagerAdapter createPagerAdapter() {
        return new BaseNewTaskFragmentPagerAdapter(getSupportFragmentManager());
    }

    private void setOnTabSelectedListener() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
                SwitchPositionToSetToolBarTitle(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void SwitchPositionToSetToolBarTitle(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                setToolBarTitle(getString(R.string.hot_zhihu));
                break;
            case 1:
                setToolBarTitle(getString(R.string.girl));
                break;
            case 2:
                setToolBarTitle(getString(R.string.mine));
                break;
        }
    }

    private void setToolBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    private void addTabToTabLayout() {
        tabLayout.removeAllTabs();
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),
                R.drawable.selector_hot,getString(R.string.hot_zhihu));
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),
                R.drawable.selector_girl, getString(R.string.girl));
        addTab(getLayoutInflater().inflate(R.layout.customtab, null),
                R.drawable.selector_user, getString(R.string.mine));
    }

    private BaseNewTaskFragmentPagerAdapter addFragmentToAdapter(BaseNewTaskFragmentPagerAdapter adapter) {
        adapter.addFragment(new TodayFragment());
        adapter.addFragment(new GirlFragment());
        adapter.addFragment(new SettingFragment());
        return adapter;
    }

    private void addTab(View view, int drawable, String title) {
        view.findViewById(R.id.img_tab).setBackgroundResource(drawable);
        TextView textView = ((TextView) view.findViewById(R.id.txt_tab));
        textView.setText(title);
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
