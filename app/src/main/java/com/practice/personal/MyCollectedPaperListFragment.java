package com.practice.personal;

import android.os.Bundle;
import android.util.ArrayMap;

import com.practice.base.BaseTodayNewsListFramgent;
import com.practice.data.Story;
import com.practice.todaynews.adapter.TodayItemAdapter;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class MyCollectedPaperListFragment extends BaseTodayNewsListFramgent implements TodayItemAdapter.onImgCollectClickListener {
    private Realm realm;
    private RealmResults<Story> results;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        RealmQuery<Story> query = realm.where(Story.class);
        results = query.findAll();
    }


    @Override
    protected void setImgCollectListener(TodayItemAdapter adapter) {
        adapter.setListener(this);
    }

    @Override
    protected void loadData() {
        adapter.setmList(results);
        todayItemAdapter.setmMap(putElementToMap(results, mMap));

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
        realm.beginTransaction();
        dayGankData.removeFromRealm();
        realm.commitTransaction();
        adapter.notifyDataSetChanged();
    }
}
