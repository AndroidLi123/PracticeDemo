package com.gank.personal;

import com.gank.data.Story;
import com.gank.data.TodayNews;
import com.gank.todaynews.view.TodayFragment;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by LiXiaoWang
 */
public class MyCollectedPaperListFragment extends TodayFragment {
    private Realm realm;
    RealmResults<Story> results;

    public MyCollectedPaperListFragment() {
    }

    @Override
    protected void init() {
        realm = Realm.getDefaultInstance();
        RealmQuery<Story> query = realm.where(Story.class);
        results = query.findAll();
        //Log.d("findsize", results.get(0).getmTitle() + "");

    }


    @Override
    protected void loadData() {
        setupDayGankDataToView(null);
    }

    @Override
    public void setupDayGankDataToView(TodayNews dayGankData) {
        adapter.setmList(results);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(realm!=null)
        realm.close();
    }
}
