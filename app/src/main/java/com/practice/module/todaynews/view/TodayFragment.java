package com.practice.module.todaynews.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;

import com.practice.common.base.BaseTodayNewsListFramgent;
import com.practice.common.util.itemanimator.ItemAnimatorFactory;
import com.practice.common.data.Story;
import com.practice.common.data.TodayNews;
import com.practice.module.setting.SwithModelEvent;
import com.practice.module.todaynews.adapter.TodayItemAdapter;
import com.practice.module.todaynews.model.TodayModelImp;
import com.practice.module.todaynews.presenter.TodayPresenter;
import com.practice.module.todaynews.presenter.TodayPresenterImp;

import de.greenrobot.event.EventBus;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class TodayFragment extends BaseTodayNewsListFramgent implements TodayView, TodayItemAdapter.onImgCollectClickListener {
    private TodayPresenter dayGankPresenter;
    private Realm realm;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();
    public TodayFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        EventBus.getDefault().register(this);
        dayGankPresenter = new TodayPresenterImp(new TodayModelImp(),this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void setUpRecyclerView(RecyclerView recyclerView) {
        super.setUpRecyclerView(recyclerView);
        recyclerView.setItemAnimator(ItemAnimatorFactory.slidein());
    }

    @Override
    public void onResume() {
        super.onResume();
        mMap.clear();
        RealmQuery<Story> query = realm.where(Story.class);
        ((TodayItemAdapter)getmBaseListAdapter()).setmMap(putElementToMap(query.findAll()));
    }

    private ArrayMap<Long, Boolean> putElementToMap(RealmResults<Story> results) {
        if (results != null) {
            for (int i = 0; i < results.size(); i++) {
                mMap.put(results.get(i).getmId(), true);
            }
        }
        return mMap;
    }

    @Override
    protected void loadData() {
        dayGankPresenter.loadData();

    }

    @Override
    public void setupDayGankDataToView(TodayNews dayGankData) {
        ((TodayItemAdapter)getmBaseListAdapter()).setmList(dayGankData.getmStories());

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
    public void onDestroy() {
        super.onDestroy();
        if (realm != null)
            realm.close();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onImgClick(Story dayGankData, boolean isChecked, ArrayMap<Long, Boolean> mMap) {
        mMap.put(dayGankData.getmId(), isChecked);
        if (isChecked) {
            copyToDB(dayGankData);
        } else {
            removeFromDB(dayGankData);
        }

    }

    private void removeFromDB(Story dayGankData) {
        realm.beginTransaction();
        RealmResults<Story> results = realm.where(Story.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getmId() == dayGankData.getmId()) {
                results.remove(i);
            }
        }
        realm.commitTransaction();
    }

    private void copyToDB(Story dayGankData) {
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealm(dayGankData);
            realm.commitTransaction();

        }
    }

    public void onEvent(SwithModelEvent event) {
        getFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();

    }
}
