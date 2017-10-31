package com.example.wen0m.sampleapp.dependency.module;


import com.example.wen0m.sampleapp.listing.sorting.SortingPresenter;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;


@Module(includes = AppModule.class)
public class SortingModule {
    @Provides
    SortingPresenter provideSortingPresenter(SharedPrefsUtil sharedPrefs) {
        return new SortingPresenter(sharedPrefs);
    }
}
