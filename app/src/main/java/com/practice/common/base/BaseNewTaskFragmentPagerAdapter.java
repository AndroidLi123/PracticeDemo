package com.practice.common.base;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2016/3/10.
 */
public class BaseNewTaskFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    public BaseNewTaskFragmentPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);

    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


}

