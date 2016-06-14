package com.practice.girl.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.practice.base.BaseListAdapter;
import com.practice.base.BaseListFragment;
import com.practice.data.GankData;
import com.practice.data.entitiy.Gank;
import com.practice.girl.adapter.GirlListAdapter;
import com.practice.girl.model.GirlModel;
import com.practice.girl.model.GirlModelImp;
import com.practice.girl.presenter.GirlPresenter;
import com.practice.girl.presenter.GirlPresenterImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXiaoWang
 */
public class GirlFragment extends BaseListFragment implements GirlView, GirlListAdapter.OnItemClickListener {
    private List<Gank> list = new ArrayList<>();
    private GirlPresenter girlPresenter;
    private GirlModel girlModel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        girlModel = new GirlModelImp();
        girlPresenter = new GirlPresenterImp(girlModel, this);

    }

    @Override
    protected BaseListAdapter onCreateAdapter() {
        GirlListAdapter adapter = new GirlListAdapter(list,mBaseActivity);
        adapter.setOnItemClickListener(this);
        return adapter;
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
        return layoutManager;
    }

    @Override
    public void setupDataToView(GankData gankData) {
        adapter.setmList(gankData.getResults());

    }

    @Override
    public void showProgress() {
        refreshView.setRefreshing(true);

    }

    @Override
    public void hideProgress() {
        refreshView.setRefreshing(false);

    }

    @Override
    public void onItemClick(ImageView imageView, String imgUrl) {
        PictureActivity.start(mBaseActivity,imgUrl);
    }

}
