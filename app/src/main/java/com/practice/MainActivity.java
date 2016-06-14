package com.practice;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.practice.base.BaseActivity;
import com.practice.base.BaseNewTaskFragmentPagerAdapter;
import com.practice.girl.view.GirlFragment;
import com.practice.setting.SettingFragment;
import com.practice.todaynews.view.TodayFragment;

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
        adapter = new BaseNewTaskFragmentPagerAdapter(getSupportFragmentManager());
        addFragmentToAdapter(adapter);
        return adapter;
    }

    private void setOnTabSelectedListener() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
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

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setToolBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    private void addTabToTabLayout() {
        tabLayout.removeAllTabs();
        addTab(getLayoutInflater().inflate(R.layout.customtab, null), R.drawable.selector_hot, "热文");
        addTab(getLayoutInflater().inflate(R.layout.customtab, null), R.drawable.selector_girl, "美图");
        addTab(getLayoutInflater().inflate(R.layout.customtab, null), R.drawable.selector_user, "我的");
    }

    private void addFragmentToAdapter(BaseNewTaskFragmentPagerAdapter adapter) {
        adapter.addFragment(new TodayFragment());
        adapter.addFragment(new GirlFragment());
        adapter.addFragment(new SettingFragment());
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
