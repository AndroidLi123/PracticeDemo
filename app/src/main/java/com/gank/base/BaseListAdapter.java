package com.gank.base;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by thinkpad on 2016/4/29.
 */

/**
 * Created by thinkpad on 2016/3/7.
 */
public abstract class BaseListAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<M> mList;

    public BaseListAdapter(List<M> mList) {
        this.mList = mList;
    }

    public void setmList(List<M> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<M> getmList() {
        return mList;
    }

    public void addmList(List<M> addList) {
        mList.addAll(addList);
        notifyDataSetChanged();

    }


    protected M getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}

