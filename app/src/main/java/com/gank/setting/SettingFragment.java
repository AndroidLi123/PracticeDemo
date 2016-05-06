package com.gank.setting;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.gank.R;

/**
 * Created by LiXiaoWang
 */
public class SettingFragment extends PreferenceFragmentCompat {
    public SettingFragment() {
    }



    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);

    }

}
