package com.example.wen0m.sampleapp.dependency.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.wen0m.sampleapp.dependency.NamedPreference;
import com.example.wen0m.sampleapp.shared.Constans;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class AppModule {
    private Context context;

    public AppModule(Application application)
    {
        context = application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return context;
    }

    @Provides
    @NamedPreference
    String providePreferenceName() {
        return Constans.PREF_NAME;
    }

    @Provides
    @Singleton
    public SharedPrefsUtil provideSharedPrefsUtil(Context context, @NamedPreference String key)
    {
        return new SharedPrefsUtil(context, key);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }


}
