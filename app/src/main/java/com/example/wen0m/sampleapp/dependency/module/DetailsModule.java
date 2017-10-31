package com.example.wen0m.sampleapp.dependency.module;


import com.example.wen0m.sampleapp.dependency.DetailedViewScope;
import com.example.wen0m.sampleapp.detailed.DetailsPresenter;
import com.example.wen0m.sampleapp.utils.NetworkUtils;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;


@Module
public class DetailsModule {
    @Provides
    @DetailedViewScope
    DetailsPresenter provideDetailsPresenter(SharedPrefsUtil sharedPrefs, NetworkUtils utils, CompositeSubscription subscription) {
        return new DetailsPresenter(sharedPrefs, utils, subscription);
    }
}
