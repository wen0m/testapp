package com.example.wen0m.sampleapp.deprecated;


import com.example.wen0m.sampleapp.dependency.DetailedViewScope;
import com.example.wen0m.sampleapp.shared.BuildingsApiService;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;


@Deprecated
@Module
public class DetailsModule {
    @Provides
    @DetailedViewScope
    DetailsPresenter provideDetailsPresenter(SharedPrefsUtil sharedPrefs, BuildingsApiService utils, CompositeSubscription subscription) {
        return new DetailsPresenter(sharedPrefs, utils, subscription);
    }
}
