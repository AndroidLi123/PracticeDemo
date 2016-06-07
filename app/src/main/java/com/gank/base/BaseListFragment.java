package com.gank.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gank.R;
import com.zhy.changeskin.SkinManager;

/**
 * Created by thinkpad on 2016/4/29.
 */
public abstract class BaseListFragment extends BaseFragment {
    protected SwipeRefreshLayout refreshView;
    protected BaseListAdapter adapter;
    protected RecyclerView recyclerView;

    protected abstract BaseListAdapter onCreateAdapter();

    protected abstract void loadData();

    protected abstract LayoutManager onCreateLayoutManager();

    protected  void setItemAnimater(RecyclerView recyclerView) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.layout_common_list, container, false);
        SkinManager.getInstance().injectSkin(parentView);
        SkinManager.getInstance().apply(getActivity());
        recyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
        refreshView = (SwipeRefreshLayout) parentView.findViewById(R.id.pull_to_refresh);
        setItemAnimater(recyclerView);
        adapter = onCreateAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(onCreateLayoutManager());
        loadData();
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();

            }
        });
        return parentView;
    }



    public BaseListAdapter getAdapter() {
        return adapter;
    }
}
