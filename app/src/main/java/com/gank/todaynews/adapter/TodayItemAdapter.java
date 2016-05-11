package com.gank.todaynews.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gank.R;
import com.gank.base.BaseListAdapter;
import com.gank.common.CycleInterpolator;
import com.gank.common.ImageLoader;
import com.gank.data.Story;
import com.gank.newsdetail.NewsDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Observable;

/**
 * Created by LiXiaoWang
 */
public class TodayItemAdapter extends BaseListAdapter<Story, TodayItemAdapter.ViewHolder> {
    private Context context;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();
    //private Realm realm;
    public onImgCollectClickListener listener;
    private Observable<List<Story>> myObservable;

    public Observable<List<Story>> getMyObservable() {
        return myObservable;
    }

    public void setListener(onImgCollectClickListener listener) {
        this.listener = listener;
    }

    public TodayItemAdapter(List<Story> list, Context context, Realm realm, ArrayMap mMap) {
        super(list);
        this.context = context;
        //this.realm = realm;
        //this.mMap = mMap;
        //myObservable = Observable.just(mList);
    }

    public void setmMap(ArrayMap<Long, Boolean> mMap) {
        this.mMap = mMap;
        Log.d("setmap","sucess");
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daygank, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Story dayGankData = mList.get(position);
        final long newsid = dayGankData.getmId();
        String imgUrl = dayGankData.getmImageUrls().get(0).getString();
        String des = dayGankData.getmTitle();
        Boolean isChecked = mMap.get(dayGankData.getmId());
        if (isChecked != null && isChecked)
            holder.check_collect.setChecked(true);
        else
            holder.check_collect.setChecked(false);
        holder.check_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox) v;
                listener.onImgClick(dayGankData,checkbox.isChecked());
              /*  mMap.put(dayGankData.getmId(), checkbox.isChecked());
                if (checkbox.isChecked()) {
                    //listener.onImgClick(dayGankData);
                    saveToDb(dayGankData);
                } else {
                    deleteToDb(dayGankData, position);
                }*/

            }
        });

        ImageLoader.getInstance().LoadImage(imgUrl, context, holder.imgMeizhi);
        holder.txtDes.setText(des);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.animate(v)
                        .setDuration(200)
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setInterpolator(new CycleInterpolator())
                        .setListener(new ViewPropertyAnimatorListener() {
                            @Override
                            public void onAnimationStart(final View view) {

                            }

                            @Override
                            public void onAnimationEnd(final View view) {
                                NewsDetailActivity.start(newsid);

                            }

                            @Override
                            public void onAnimationCancel(final View view) {

                            }
                        })
                        .withLayer()
                        .start();
            }
        });
    }

   /* private void deleteToDb(Story dayGankData, int position) {
        if (context instanceof MyCollectedPaperActivity) {
            realm.beginTransaction();
            dayGankData.removeFromRealm();
            realm.commitTransaction();
            notifyDataSetChanged();
            myObservable = Observable.just(mList);

        } else {
            realm.beginTransaction();
            RealmResults<Story> results = realm.where(Story.class).findAll();
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i).getmId() == dayGankData.getmId()) {
                    results.remove(i);

                }
            }
            realm.commitTransaction();

        }


    }

    private void saveToDb(final Story dayGankData) {
        if (realm != null) {
            realm.beginTransaction();
            realm.copyToRealm(dayGankData);
            realm.commitTransaction();

        }
    }*/

    public interface onImgCollectClickListener {
        void onImgClick(Story dayGankData,boolean isChecked);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_meizhi)
        ImageView imgMeizhi;
        @Bind(R.id.txt_des)
        TextView txtDes;
        @Bind(R.id.collect)
        CheckBox check_collect;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
