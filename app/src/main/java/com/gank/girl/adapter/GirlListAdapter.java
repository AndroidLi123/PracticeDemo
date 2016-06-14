package com.gank.girl.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gank.R;
import com.gank.base.BaseListAdapter;
import com.gank.common.ImageLoaderUtil;
import com.gank.common.MyImageLoader;
import com.gank.data.entitiy.Gank;
import com.gank.widget.RatioImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LiXiaoWang
 */
public class GirlListAdapter extends BaseListAdapter<Gank, GirlListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;

    public GirlListAdapter(List list, Context context) {
        super(list);
        this.context = context;
        setHasStableIds(true);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public long getItemId(int position) {
        return mList.get(position).get_id().hashCode();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Gank gank = getItem(position);
        ImageLoaderUtil.getInstance().loadImage(context,createBuilder(holder, gank).build());
        onItemViewClick(holder, gank);

    }

    @NonNull
    private MyImageLoader.Builder createBuilder(ViewHolder holder, Gank gank) {
        MyImageLoader.Builder builder = new MyImageLoader.Builder();
        builder.imgView(holder.imgMeizhi);
        builder.url(gank.getUrl());
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE);
        return builder;
    }

    private void onItemViewClick(final ViewHolder holder, final Gank gank) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.imgMeizhi, gank.getUrl());

            }
        });
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_meizhi)
        public RatioImageView imgMeizhi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView imageView, String imgUrl);
    }
}
