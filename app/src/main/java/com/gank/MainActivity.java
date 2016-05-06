package com.gank;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.gank.base.BaseActivity;
import com.gank.base.BaseNewTaskFragmentPagerAdapter;
import com.gank.girl.view.GirlFragment;
import com.gank.setting.SettingFragment;
import com.gank.todaynews.view.TodayFragment;
import com.gigamole.library.NavigationTabBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.vp_horizontal_ntb)
    ViewPager viewPager;
    @Bind(R.id.ntb_horizontal)
    NavigationTabBar navigationTabBar;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
    private BaseNewTaskFragmentPagerAdapter adapter;
    private TodayFragment dayGankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        dayGankFragment = new TodayFragment();
        adapter = new BaseNewTaskFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TodayFragment(), "干货");
        adapter.addFragment(new GirlFragment(),"美图");
        adapter.addFragment(new SettingFragment(),"设置");
        final String[] colors = getResources().getStringArray(R.array.default_preview);

        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_first), Color.parseColor(colors[0]), "Heart"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_second), Color.parseColor(colors[1]), "Cup"));
        models.add(new NavigationTabBar.Model(
                getResources().getDrawable(R.drawable.ic_third), Color.parseColor(colors[2]), "Diploma"));
        viewPager.setAdapter(adapter);
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

}
