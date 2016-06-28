package com.practice.common.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.practice.R;

/**
 * Created by LiXiaoWang
 */
public abstract class BaseListFragment extends BaseFragment {
    private BaseListAdapter mBaseListAdapter;
    private SwipeRefreshLayout mBaserefreshView;
    private RecyclerView mBaserecyclerView;
    private LayoutManager mBaselayoutmanager;
    protected abstract BaseListAdapter onCreateAdapter();

    protected abstract void loadData();

    protected abstract LayoutManager onCreateLayoutManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.layout_common_list, container, false);
        mBaserecyclerView = (RecyclerView) parentView.findViewById(R.id.recyclerView);
        mBaserefreshView = (SwipeRefreshLayout) parentView.findViewById(R.id.pull_to_refresh);
        mBaseListAdapter = onCreateAdapter();
        mBaselayoutmanager = onCreateLayoutManager();
        setUpRecyclerView( mBaserecyclerView);
        setUpRefreshView(mBaserefreshView);
        loadData();
        return parentView;
    }


    private void setUpRefreshView(SwipeRefreshLayout refreshView) {
        refreshView.setRefreshing(true);
        setOnRefreshListener(refreshView);
    }

    private void setOnRefreshListener(SwipeRefreshLayout refreshView) {
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    protected void setUpRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(mBaseListAdapter);
        recyclerView.setLayoutManager(mBaselayoutmanager);
    }

    public BaseListAdapter getmBaseListAdapter() {
        return mBaseListAdapter;
    }

    public RecyclerView getmBaserecyclerView() {
        return mBaserecyclerView;
    }

    public SwipeRefreshLayout getmBaserefreshView() {
        return mBaserefreshView;
    }

    public LayoutManager getmBaselayoutmanager() {
        return mBaselayoutmanager;
    }
}
