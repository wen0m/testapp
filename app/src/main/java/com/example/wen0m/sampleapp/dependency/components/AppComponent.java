package com.example.wen0m.sampleapp.dependency.components;


import android.content.Context;
import com.example.wen0m.sampleapp.dependency.module.AppModule;
import com.example.wen0m.sampleapp.dependency.module.ApiModule;
import com.example.wen0m.sampleapp.dependency.module.SortingModule;
import com.example.wen0m.sampleapp.mvp.presenters.DetailPresenter;
import com.example.wen0m.sampleapp.mvp.presenters.FavouritesPresenter;
import com.example.wen0m.sampleapp.mvp.presenters.ListPresenter;
import com.example.wen0m.sampleapp.mvp.presenters.SortingPresenter;
import com.example.wen0m.sampleapp.shared.BuildingsApiService;
import com.example.wen0m.sampleapp.shared.SharedPrefsUtil;
import com.example.wen0m.sampleapp.shared.UfcApiService;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
//@Component(modules = { AppModule.class, SortingModule.class, NetworkModule.class})
@Component(modules = { AppModule.class, SortingModule.class, ApiModule.class})

public interface AppComponent {
    Context getContext();
    SharedPrefsUtil getSharedPrefs();
    BuildingsApiService getBuildingsService();
    UfcApiService getUfcService();

    void inject(ListPresenter presenter);
    void inject(DetailPresenter presenter);
    void inject(SortingPresenter presenter);
    void inject(FavouritesPresenter presenter);

//    DetailedComponent add(DetailsModule module);
//    ListingComponent add(ListingModule module);
}
