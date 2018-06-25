package com.example.wen0m.sampleapp.dependency.module;


import com.example.wen0m.sampleapp.mvp.presenters.SortingPresenter;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;


@Module(includes = AppModule.class)
public class SortingModule {
    @Provides
    SortingPresenter provideSortingPresenter() {
        return new SortingPresenter();
    }
}
