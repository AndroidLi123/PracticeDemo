package com.gank.todaynews.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gank.R;
import com.gank.base.BaseListAdapter;
import com.gank.common.CycleInterpolator;
import com.gank.common.ImageLoader;
import com.gank.data.TodayNews;
import com.gank.newsdetail.NewsDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LiXiaoWang
 */
public class TodayItemAdapter extends BaseListAdapter<TodayNews.Story, TodayItemAdapter.ViewHolder> {
    private Context context;
    public TodayItemAdapter(List<TodayNews.Story> list,Context context) {
        super(list);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daygank, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodayNews.Story dayGankData = mList.get(position);
        final long newsid = dayGankData.getId();
        String imgUrl = dayGankData.getImageUrls().get(0);
        String des = dayGankData.getTitle();
        ImageLoader.getInstance().LoadImage(imgUrl, context,holder.imgMeizhi);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_meizhi)
        ImageView imgMeizhi;
        @Bind(R.id.txt_des)
        TextView txtDes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
