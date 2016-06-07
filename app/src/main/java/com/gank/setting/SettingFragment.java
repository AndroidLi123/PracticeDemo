package com.gank.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;

import com.gank.R;
import com.gank.personal.MyCollectedPaperActivity;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;
import com.zhy.changeskin.SkinManager;

import de.greenrobot.event.EventBus;

/**
 * Created by LiXiaoWang
 */
public class SettingFragment extends PreferenceFragmentCompatDividers implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private Preference myCollected;
    CheckBoxPreference switchImageStragy;
    CheckBoxPreference switchModel;

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        myCollected = findPreference(getString(R.string.notify_priority));
        myCollected.setOnPreferenceClickListener(this);
        switchImageStragy = (CheckBoxPreference) findPreference(getString(R.string.onlywifi));
        switchImageStragy.setOnPreferenceChangeListener(this);
        switchModel = (CheckBoxPreference) findPreference(getString(R.string.switch_model));
        switchModel.setOnPreferenceChangeListener(this);

    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == myCollected) {
            startActivity(new Intent(getActivity(), MyCollectedPaperActivity.class));
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference == switchModel) {
            Boolean isNightMode = Boolean.valueOf(o.toString());
            if (isNightMode) {
                SkinManager.getInstance().changeSkin("night");
            } else {
                SkinManager.getInstance().changeSkin("light");

            }
            EventBus.getDefault().post(new SwithModelEvent());

        }
        return true;
    }
}
