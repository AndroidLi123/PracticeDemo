package com.practice.module.todaynews.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;

import com.practice.common.base.BaseTodayNewsListFramgent;
import com.practice.common.data.Story;
import com.practice.common.data.TodayNews;
import com.practice.common.util.DbHelper;
import com.practice.common.util.itemanimator.ItemAnimatorFactory;
import com.practice.module.setting.SwithModelEvent;
import com.practice.module.todaynews.adapter.TodayItemAdapter;
import com.practice.module.todaynews.model.TodayModelImp;
import com.practice.module.todaynews.presenter.TodayPresenter;
import com.practice.module.todaynews.presenter.TodayPresenterImp;

import de.greenrobot.event.EventBus;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class TodayFragment extends BaseTodayNewsListFramgent implements TodayView, TodayItemAdapter.onImgCollectClickListener {
    private TodayPresenter dayGankPresenter;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();
    private DbHelper<Story> dbHelper;

    public TodayFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        EventBus.getDefault().register(this);
        dayGankPresenter = new TodayPresenterImp(new TodayModelImp(), this);
        dbHelper = new DbHelper<>(Story.class);
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
        ((TodayItemAdapter) getmBaseListAdapter()).setmMap(putElementToMap(findAllStory()));
    }

    private RealmResults<Story> findAllStory() {
        return dbHelper.findAll();

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
        ((TodayItemAdapter) getmBaseListAdapter()).setmList(dayGankData.getmStories());

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
        if (dbHelper.getRealm() != null)
            dbHelper.getRealm().close();
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
        RealmResults<Story> target = dbHelper.query().equalTo("mId", dayGankData.getmId()).findAll();
        dbHelper.remove(target.first());
    }

    private void copyToDB(Story dayGankData) {
        dbHelper.copy(dayGankData);
    }

    public void onEvent(SwithModelEvent event) {
        getFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();

    }
}
