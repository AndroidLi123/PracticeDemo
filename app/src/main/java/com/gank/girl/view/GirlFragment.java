package com.gank.girl.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.gank.base.BaseListAdapter;
import com.gank.base.BaseListFragment;
import com.gank.data.GankData;
import com.gank.data.entitiy.Gank;
import com.gank.girl.adapter.GirlListAdapter;
import com.gank.girl.model.GirlModel;
import com.gank.girl.model.GirlModelImp;
import com.gank.girl.presenter.GirlPresenter;
import com.gank.girl.presenter.GirlPresenterImp;

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
        GirlListAdapter adapter = new GirlListAdapter(list,getActivity());
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
