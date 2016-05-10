package com.gank.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.Preference;

import com.gank.R;
import com.gank.personal.MyCollectedPaperActivity;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;

/**
 * Created by LiXiaoWang
 */
public class SettingFragment extends PreferenceFragmentCompatDividers {
    Preference myCollected;
    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        myCollected = findPreference(getString(R.string.notify_priority));
        myCollected.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), MyCollectedPaperActivity.class));
                return true;
            }
        });

    }
}
