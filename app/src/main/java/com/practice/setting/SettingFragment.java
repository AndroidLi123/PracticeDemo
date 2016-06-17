package com.practice.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;

import com.bumptech.glide.Glide;
import com.practice.PracticeApplication;
import com.practice.R;
import com.practice.common.DataCleanManager;
import com.practice.personal.MyCollectedPaperActivity;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;
import com.zhy.changeskin.SkinManager;

import de.greenrobot.event.EventBus;

/**
 * Created by LiXiaoWang
 */
public class SettingFragment extends PreferenceFragmentCompatDividers implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private Preference preference_collection;
    private Preference preference_deletecache;
    private CheckBoxPreference switchModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        CheckBoxPreference switchImageStragy = (CheckBoxPreference) findPreference(getString(R.string.onlywifi));
        preference_collection = findPreference(getString(R.string.notify_priority));
        preference_deletecache = findPreference(getString(R.string.delete_cache));
        switchModel = (CheckBoxPreference) findPreference(getString(R.string.switch_model));
        preference_collection.setOnPreferenceClickListener(this);
        preference_deletecache.setOnPreferenceClickListener(this);
        switchImageStragy.setOnPreferenceChangeListener(this);
        switchModel.setOnPreferenceChangeListener(this);
        setPreference_DeleteCacheSummary();

    }

    private void setPreference_DeleteCacheSummary() {
        try {
            preference_deletecache.setSummary(DataCleanManager.getCacheSize(PracticeApplication.sContext.getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == preference_collection) {
            onPreferenceCollectionClick();
        } else if (preference == preference_deletecache) {
            onPreferenceDeleteCacheClilck();

        }
        return true;
    }

    private void onPreferenceCollectionClick() {
        startActivity(new Intent(getActivity(), MyCollectedPaperActivity.class));
    }

    private void onPreferenceDeleteCacheClilck() {
        AlertDialog alertDialog = createDiaLogBuilder().create();
        alertDialog.show();
    }

    @NonNull
    private AlertDialog.Builder createDiaLogBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("确定要清除缓存吗?");
        setBuilderPositiveButton(builder);
        setBuilderNegativeButton(builder);
        return builder;
    }

    private void setBuilderNegativeButton(AlertDialog.Builder builder) {
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    private void setBuilderPositiveButton(AlertDialog.Builder builder) {
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onPositiveClick();

            }
        });
    }

    private void onPositiveClick() {
        new Thread() {
            public void run() {
                DataCleanManager.cleanInternalCache(PracticeApplication.sContext);
                Glide.get(getActivity()).clearDiskCache();
                EventBus.getDefault().post(new ClearCacheEvent());

            }
        }.start();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference == switchModel) {
            preferenceEqualsSwithModel(o);
        }
        return true;
    }

    private void preferenceEqualsSwithModel(Object o) {
        Boolean isNightMode = Boolean.valueOf(o.toString());
        SkinManager.getInstance().changeSkin(isNightMode ? "night" : "light");
      /*  ((SkinnableActivity) getActivity()).setDayNightMode(isNightMode ? AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);*/
        EventBus.getDefault().post(new SwithModelEvent());
    }

    public void onEventMainThread(ClearCacheEvent event) {
        setPreference_DeleteCacheSummary();
    }
}
