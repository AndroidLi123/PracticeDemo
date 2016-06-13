package com.gank.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by LiXiaoWang
 */
public class BaseFragment extends Fragment{
    protected Activity mBaseActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (Activity) context;
    }
}
