package com.gank.todaynews.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gank.base.BaseListAdapter;
import com.gank.base.BaseListFragment;
import com.gank.data.TodayNews;
import com.gank.todaynews.adapter.TodayItemAdapter;
import com.gank.todaynews.model.TodayModel;
import com.gank.todaynews.model.TodayModelImp;
import com.gank.todaynews.presenter.TodayPresenter;
import com.gank.todaynews.presenter.TodayPresenterImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class TodayFragment extends BaseListFragment implements TodayView {
    private TodayPresenter dayGankPresenter;
    private TodayModel dayGankModel;
    private List<TodayNews.Story> dayGankDatas = new ArrayList<>();

    public TodayFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayGankModel = new TodayModelImp(getContext());
        dayGankPresenter = new TodayPresenterImp(dayGankModel, this);

    }

    @Override
    protected BaseListAdapter onCreateAdapter() {
        return new TodayItemAdapter(dayGankDatas,getActivity());

    }

    @Override
    protected void loadData() {
        dayGankPresenter.loadData();

    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }




    @Override
    public void setupDayGankDataToView(TodayNews dayGankData) {
        adapter.setmList(dayGankData.getStories());
    }

    @Override
    public void showProgress() {
        refreshView.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshView.setRefreshing(false);

    }
}
