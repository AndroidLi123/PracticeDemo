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
        View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.img_tab).setBackgroundResource(R.drawable.selector_hot);
        TextView textView1 =  ((TextView)view1.findViewById(R.id.txt_tab));
        textView1.setText("热文");
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));

        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.img_tab).setBackgroundResource(R.drawable.selector_girl);
        ((TextView)view2.findViewById(R.id.txt_tab)).setText("美图");
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));


        View view3 = getLayoutInflater().inflate(R.layout.customtab, null);
        view3.findViewById(R.id.img_tab).setBackgroundResource(R.drawable.selector_user);
        ((TextView)view3.findViewById(R.id.txt_tab)).setText("我的");
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));
   /*     for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable d = null;
            switch (i) {
                case 0:
                    d = getResources().getDrawable(R.drawable.selector_hot);
                    break;
                case 1:
                    d = getResources().getDrawable(R.drawable.selector_girl);
                    break;
                case 2:
                    d = getResources().getDrawable(R.drawable.selector_user);
                    break;

            }
            if (tab != null) {
                tab.setIcon(d);

            }
        }*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

}
