package com.gank.todaynews.adapter;

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
import com.gank.R;
import com.gank.base.BaseListAdapter;
import com.gank.common.CycleInterpolator;
import com.gank.common.ImageLoaderUtil;
import com.gank.common.MyImageLoader;
import com.gank.data.Story;
import com.gank.newsdetail.NewsDetailActivity;
import com.zhy.changeskin.SkinManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    public TodayItemAdapter(List<Story> list, Context context, boolean animated) {
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
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daygank, parent, false);
        SkinManager.getInstance().injectSkin(view);
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
        ImageLoaderUtil.getInstance().loadImage(context, getBuilder(holder, imgUrl).build());
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
    private MyImageLoader.Builder getBuilder(ViewHolder holder, String imgUrl) {
        MyImageLoader.Builder builder = new MyImageLoader.Builder();
        builder.imgView(holder.imgMeizhi);
        builder.url(imgUrl);
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder;
    }

    private void setOnItemViewClickListener(ViewHolder holder, final long newsid) {
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

        }
    }
}
