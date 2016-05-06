package com.gank.girl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gank.R;
import com.gank.base.BaseListAdapter;
import com.gank.common.ImageLoader;
import com.gank.data.entitiy.Gank;

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

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_girl, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Gank gank = getItem(position);
        holder.itemView.setTag(gank.get_id());
        ImageLoader.getInstance().LoadImageWithDiskCacheStrategyALL(gank.getUrl(),context).into(holder.imgMeizhi);
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
        ImageView imgMeizhi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ImageView imageView, String imgUrl);
    }
}
