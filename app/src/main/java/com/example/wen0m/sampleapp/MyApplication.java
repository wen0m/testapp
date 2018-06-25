package com.example.wen0m.sampleapp;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wen0m.sampleapp.dependency.components.AppComponent;
import com.example.wen0m.sampleapp.dependency.components.DaggerAppComponent;
import com.example.wen0m.sampleapp.deprecated.DetailedComponent;
import com.example.wen0m.sampleapp.deprecated.ListingComponent;
import com.example.wen0m.sampleapp.dependency.module.AppModule;
import com.example.wen0m.sampleapp.dependency.module.NetworkModule;
import com.example.wen0m.sampleapp.dependency.module.SortingModule;
import com.example.wen0m.sampleapp.mvp.models.DaoMaster;
import com.example.wen0m.sampleapp.mvp.models.DaoSession;
import com.example.wen0m.sampleapp.shared.Constans;
//import com.squareup.leakcanary.LeakCanary;


public class MyApplication extends Application {
    private static MyApplication _INSTANCE = null;
    private DaoSession daoSession;
    private AppComponent appComponent;
    private DetailedComponent detailsComponent;
    private ListingComponent listingComponent;

    public static MyApplication getInstance() {
        return _INSTANCE;
    }

    @Override
    public void onCreate() {
        _INSTANCE = this;
        super.onCreate();
        appComponent = createAppComponent();
//        LeakCanary.install(this);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constans.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
}

    public DaoSession getSession() {
        return daoSession;
    }

    public static Context getContext() {
        return _INSTANCE.getApplicationContext();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .sortingModule(new SortingModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

//    public DetailedComponent createDetailsComponent()
//    {
//        detailsComponent = appComponent.add(new DetailsModule());
//        return detailsComponent;
//    }
//
//    public void releaseDetailsComponent()
//    {
//        detailsComponent = null;
//    }
//
//    public ListingComponent createListingComponent()
//    {
//        listingComponent = appComponent.add(new ListingModule());
//        return listingComponent;
//    }
//
//    public void releaseListingComponent()
//    {
//        listingComponent = null;
//    }
//
//    public ListingComponent getListingComponent()
//    {
//        return listingComponent;
//    }
}
