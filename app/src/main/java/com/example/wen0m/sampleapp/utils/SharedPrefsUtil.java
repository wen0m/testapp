package com.example.wen0m.sampleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.wen0m.sampleapp.dependency.NamedPreference;
import com.example.wen0m.sampleapp.listing.sorting.SortOption;
import com.example.wen0m.sampleapp.shared.Constans;
import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class SharedPrefsUtil {

    private SharedPreferences pref;

    @Inject
    public SharedPrefsUtil(Context context, @NamedPreference String key)
    {
        pref = context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    public void setSortingOption(SortOption sortType)
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Constans.PREF_NAME_SORTING, sortType.getOption());
        editor.apply();
    }

    public int getOption() {
        return pref.getInt(Constans.PREF_NAME_SORTING, 0);
    }

    public boolean isFavourite(Long Id) {
        if(pref.getLong(Constans.PREF_NAME_FAVOURITE+Id, 0) == Id) {
            return true;
        } else
            return false;
    }

    public void setUnFavourite(Long Id) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(Constans.PREF_NAME_FAVOURITE + Id).apply();
    }

    public void setFavourite(Long Id) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(Constans.PREF_NAME_FAVOURITE + Id, Id).apply();
    }

}
