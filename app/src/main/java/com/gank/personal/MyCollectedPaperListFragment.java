package com.gank.personal;

import android.os.Bundle;
import android.util.ArrayMap;

import com.gank.base.BaseTodayNewsListFramgent;
import com.gank.data.Story;
import com.gank.todaynews.adapter.TodayItemAdapter;

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
        if (results != null) {
            for (int i = 0; i < results.size(); i++) {
                mMap.put(results.get(i).getmId(), true);

            }
        }
    }

    @Override
    protected void setImgCollectListener(TodayItemAdapter adapter) {
        adapter.setListener(this);
    }

    @Override
    protected void loadData() {
        adapter.setmList(results);
        todayItemAdapter.setmMap(mMap);

    }

    @Override
    public void onImgClick(Story dayGankData, boolean isChecked) {
        if (!isChecked) {
            realm.beginTransaction();
            dayGankData.removeFromRealm();
            realm.commitTransaction();
            adapter.notifyDataSetChanged();
        }

    }
}
