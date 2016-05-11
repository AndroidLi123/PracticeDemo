package com.gank.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gank.data.Story;
import com.gank.todaynews.adapter.TodayItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseTodayNewsListFramgent extends BaseListFragment {

    private List<Story> dayGankDatas = new ArrayList<>();
    protected TodayItemAdapter todayItemAdapter;

    protected abstract void setImgCollectListener(TodayItemAdapter adapter);

    @Override
    protected BaseListAdapter onCreateAdapter() {
        todayItemAdapter = new TodayItemAdapter(dayGankDatas, getActivity(), null, null);
        setImgCollectListener(todayItemAdapter);
        return todayItemAdapter;
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }
}
