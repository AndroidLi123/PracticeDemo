package com.gank.todaynews.adapter;

import android.content.Context;
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
    private boolean animated;
    public onImgCollectClickListener listener;

    public void setListener(onImgCollectClickListener listener) {
        this.listener = listener;
    }

    public TodayItemAdapter(List<Story> list, Context context, boolean animated) {
        super(list);
        this.context = context;
        this.animated = animated;
        setHasStableIds(true);

    }

    public void setmMap(ArrayMap<Long, Boolean> mMap) {
        this.mMap = mMap;
        notifyDataSetChanged();
    }

    @Override
    public void setmList(List<Story> stories) {
        mList = stories;
        //if (animated)
            notifyItemRangeInserted(getItemCount(), stories.size());
       /* else
            notifyDataSetChanged();*/

    }

    @Override
    public void addmList(List<Story> addList) {
        mList.addAll(addList);
       /* if (animated)
            notifyItemRangeInserted(getItemCount(), addList.size());
        else*/
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
        // holder.setIsRecyclable(false);
        final Story dayGankData = mList.get(position);
        final long newsid = dayGankData.getmId();
        String imgUrl = dayGankData.getmImageUrls().get(0).getString();
        String des = dayGankData.getmTitle();
        holder.cardView.setTag(des);
        Boolean isChecked = mMap.get(dayGankData.getmId());
        if (isChecked != null && isChecked)
            holder.check_collect.setChecked(true);
        else
            holder.check_collect.setChecked(false);
        holder.check_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkbox = (CheckBox) v;
                listener.onImgClick(dayGankData, checkbox.isChecked(),mMap);
                //mMap.put(dayGankData.getmId(),checkbox.isChecked());
                //notifyItemChanged(holder.getLayoutPosition());

            }
        });

        MyImageLoader.Builder builder = new MyImageLoader.Builder();
        builder.imgView(holder.imgMeizhi);
        builder.url(imgUrl);
        ImageLoaderUtil.getInstance().loadImage(context, builder.build());
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

    @Override
    public long getItemId(int position) {
        return mList.get(position).hashCode();
    }

    public interface onImgCollectClickListener {
        void onImgClick(Story dayGankData, boolean isChecked,ArrayMap<Long, Boolean> mMap);
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
