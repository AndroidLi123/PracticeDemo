package com.practice.module.todaynews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding.view.RxView;
import com.practice.R;
import com.practice.common.base.BaseListAdapter;
import com.practice.common.util.CycleInterpolator;
import com.practice.common.util.ImageLoaderUtil;
import com.practice.common.util.ImageLoader;
import com.practice.common.data.Story;
import com.practice.module.newsdetail.NewsDetailActivity;
import com.zhy.changeskin.SkinManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by LiXiaoWang
 */
public class TodayItemAdapter extends BaseListAdapter<Story, TodayItemAdapter.ViewHolder> {
    private Context context;
    private ArrayMap<Long, Boolean> mMap = new ArrayMap<>();
    public onImgCollectClickListener listener;

    public void setListener(onImgCollectClickListener listener) {
        this.listener = listener;
    }

    public TodayItemAdapter(List<Story> list, Context context) {
        super(list);
        this.context = context;
        setHasStableIds(true);

    }

    public void setmMap(ArrayMap<Long, Boolean> mMap) {
        this.mMap = mMap;
        notifyDataSetChanged();
    }

    @Override
    public void setmList(List<Story> stories) {
        mList = stories;
        notifyItemRangeInserted(getItemCount(), stories.size());
    }

    @Override
    public void addmList(List<Story> addList) {
        mList.addAll(addList);
        notifyItemRangeInserted(getItemCount(), addList.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daygank, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Story dayGankData = mList.get(position);
        String imgUrl = dayGankData.getmImageUrls().get(0).getString();
        holder.cardView.setTag(dayGankData.getmTitle());
        holder.txtDes.setText(dayGankData.getmTitle());
        Boolean isChecked = mMap.get(dayGankData.getmId());
        holder.check_collect.setChecked(isChecked != null && isChecked);
        setOnCheckBoxClickListener(holder, dayGankData);
        ImageLoaderUtil.getInstance().loadImage(context, createBuilder(holder, imgUrl).build());
        setOnItemViewClickListener(holder, dayGankData.getmId());
    }

    private void setOnCheckBoxClickListener(ViewHolder holder, final Story dayGankData) {
        holder.check_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImgClick(dayGankData, ((CheckBox) v).isChecked(), mMap);
            }
        });
    }

    @NonNull
    private ImageLoader.Builder createBuilder(ViewHolder holder, String imgUrl) {
        ImageLoader.Builder builder = new ImageLoader.Builder();
        builder.imgView(holder.imgMeizhi);
        builder.url(imgUrl);
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder;
    }

    private void setOnItemViewClickListener(final ViewHolder holder, final long newsid) {
        RxView.clicks(holder.itemView)
                .throttleFirst(1000, TimeUnit.MILLISECONDS) // 设置防抖间隔为 500ms
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        onItemViewClickEvent(newsid, holder.itemView);
                    }
                });
    }

    private void onItemViewClickEvent(final long newsid, View view) {
        ViewCompat.animate(view)
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

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    public interface onImgCollectClickListener {
        void onImgClick(Story dayGankData, boolean isChecked, ArrayMap<Long, Boolean> mMap);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_meizhi)
        ImageView imgMeizhi;
        @Bind(R.id.txt_des)
        TextView txtDes;
        @Bind(R.id.collect)
        CheckBox check_collect;
        @Bind(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            SkinManager.getInstance().injectSkin(view);

        }
    }
}
