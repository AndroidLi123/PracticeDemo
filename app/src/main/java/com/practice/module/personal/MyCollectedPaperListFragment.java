package com.practice.module.personal;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;

import com.practice.common.base.BaseTodayNewsListFramgent;
import com.practice.common.base.BaseView;
import com.practice.common.util.DbHelper;
import com.practice.common.util.itemanimator.ItemAnimatorFactory;
import com.practice.common.data.Story;
import com.practice.module.todaynews.adapter.TodayItemAdapter;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class MyCollectedPaperListFragment extends BaseTodayNewsListFramgent implements BaseView, TodayItemAdapter.onImgCollectClickListener {
    private Realm realm;
    private RealmResults<Story> results;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();
    private DbHelper<Story>dbHelper;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        RealmQuery<Story> query = realm.where(Story.class);
        results = query.findAll();
        dbHelper = new DbHelper<>(Story.class);
    }

    @Override
    protected void setUpRecyclerView(RecyclerView recyclerView) {
        super.setUpRecyclerView(recyclerView);
        recyclerView.setItemAnimator(ItemAnimatorFactory.slidein());
    }

    @Override
    protected void loadData() {
        ((TodayItemAdapter) getmBaseListAdapter()).setmMap(putElementToMap(results, mMap));
        ((TodayItemAdapter) getmBaseListAdapter()).setmList(results);
        hideProgress();
    }

    private ArrayMap<Long, Boolean> putElementToMap(RealmResults<Story> results, ArrayMap<Long, Boolean> mMap) {
        if (results != null) {
            for (int i = 0; i < results.size(); i++) {
                mMap.put(results.get(i).getmId(), true);
            }
        }
        return mMap;
    }

    @Override
    public void onImgClick(Story dayGankData, boolean isChecked, ArrayMap<Long, Boolean> mMap) {
        if (!isChecked) {
            removeDataFromDB(dayGankData);
        }

    }

    private void removeDataFromDB(Story dayGankData) {
        dbHelper.remove(dayGankData);
        getmBaseListAdapter().notifyDataSetChanged();
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

    }
}
