package com.practice.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.practice.data.Story;
import com.practice.itemanimator.ItemAnimatorFactory;
import com.practice.todaynews.adapter.TodayItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseTodayNewsListFramgent extends BaseListFragment {
    private List<Story> dayGankDatas = new ArrayList<>();
    protected TodayItemAdapter todayItemAdapter;
    private boolean animated = true;
    protected abstract void setImgCollectListener(TodayItemAdapter adapter);

    @Override
    protected BaseListAdapter onCreateAdapter() {
        todayItemAdapter = new TodayItemAdapter(dayGankDatas, getActivity(), animated);
        setImgCollectListener(todayItemAdapter);
        animated = false;
        return todayItemAdapter;
    }

    @Override
    protected void setItemAnimater(RecyclerView recyclerView) {
        super.setItemAnimater(recyclerView);
        recyclerView.setItemAnimator(ItemAnimatorFactory.slidein());
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }


}
