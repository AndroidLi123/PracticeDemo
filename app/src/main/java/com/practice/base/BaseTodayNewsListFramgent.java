package com.practice.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.data.Story;
import com.practice.todaynews.adapter.TodayItemAdapter;

import java.util.ArrayList;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseTodayNewsListFramgent extends BaseListFragment implements TodayItemAdapter.onImgCollectClickListener {
    @Override
    protected BaseListAdapter onCreateAdapter() {
        return new TodayItemAdapter(new ArrayList<Story>(), getActivity());
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ((TodayItemAdapter) getmBaseListAdapter()).setListener(this);
        return view;
    }

    @Override
    public void onImgClick(Story dayGankData, boolean isChecked, ArrayMap<Long, Boolean> mMap) {

    }
}
