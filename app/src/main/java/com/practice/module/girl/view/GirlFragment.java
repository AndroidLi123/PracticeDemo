package com.practice.module.girl.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.practice.common.base.BaseListAdapter;
import com.practice.common.base.BaseListFragment;
import com.practice.common.data.GankData;
import com.practice.common.data.entitiy.Gank;
import com.practice.module.girl.adapter.GirlListAdapter;
import com.practice.module.girl.model.GirlModelImp;
import com.practice.module.girl.presenter.GirlPresenter;
import com.practice.module.girl.presenter.GirlPresenterImp;

import java.util.ArrayList;

/**
 * Created by LiXiaoWang
 */
public class GirlFragment extends BaseListFragment implements GirlView, GirlListAdapter.OnItemClickListener {
    private GirlPresenter girlPresenter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        girlPresenter = new GirlPresenterImp(new GirlModelImp(), this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ((GirlListAdapter) getmBaseListAdapter()).setOnItemClickListener(this);
        addOnScrollListener();
        return view;
    }

    private void addOnScrollListener() {
        getmBaserecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                ((StaggeredGridLayoutManager) getmBaselayoutmanager()).invalidateSpanAssignments();
                super.onScrollStateChanged(recyclerView, newState);

            }
        });
    }

    @Override
    protected BaseListAdapter onCreateAdapter() {
        return new GirlListAdapter(new ArrayList<Gank>(), mBaseActivity);
    }

    @Override
    protected void loadData() {
        girlPresenter.loadGirls();

    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        final StaggeredGridLayoutManager layoutManager
                = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        return layoutManager;
    }

    @Override
    public void setupDataToView(GankData gankData) {
        getmBaseListAdapter().setmList(gankData.getResults());

    }

    @Override
    public void showProgress() {
        getmBaserefreshView().setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        getmBaserefreshView().setRefreshing(false);
    }

    @Override
    public void onItemClick(ImageView imageView, String imgUrl) {
        PictureActivity.start(mBaseActivity, imgUrl);
    }

}
