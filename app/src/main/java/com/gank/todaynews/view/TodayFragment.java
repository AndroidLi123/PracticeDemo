package com.gank.todaynews.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gank.base.BaseListAdapter;
import com.gank.base.BaseListFragment;
import com.gank.data.Story;
import com.gank.data.TodayNews;
import com.gank.todaynews.adapter.TodayItemAdapter;
import com.gank.todaynews.model.TodayModel;
import com.gank.todaynews.model.TodayModelImp;
import com.gank.todaynews.presenter.TodayPresenter;
import com.gank.todaynews.presenter.TodayPresenterImp;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by thinkpad on 2016/4/29.
 */
public class TodayFragment extends BaseListFragment implements TodayView, TodayItemAdapter.onImgCollectClickListener {
    private TodayPresenter dayGankPresenter;
    private TodayModel dayGankModel;
    private List<Story> dayGankDatas = new ArrayList<>();
    private Realm realm;
    private RealmAsyncTask transaction;

    public TodayFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        dayGankModel = new TodayModelImp(getContext());
        dayGankPresenter = new TodayPresenterImp(dayGankModel, this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected BaseListAdapter onCreateAdapter() {
        TodayItemAdapter todayItemAdapter = new TodayItemAdapter(dayGankDatas, getActivity(), realm);
        todayItemAdapter.setListener(this);
        return todayItemAdapter;
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
        adapter.setmList(dayGankData.getmStories());
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
    public void onDestroy() {
        super.onDestroy();
        if(realm!=null)
        realm.close();

    }

    @Override
    public void onImgClick(final Story dayGankData) {
        Log.d("onimage","click");
        realm.beginTransaction();
        transaction = realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                if(realm!=null) {
                    realm.copyToRealm(dayGankData);

                }
            }
        }, null);
        realm.commitTransaction();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }

}
