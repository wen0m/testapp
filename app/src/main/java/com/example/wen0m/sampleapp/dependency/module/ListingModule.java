package com.example.wen0m.sampleapp.dependency.module;


import com.example.wen0m.sampleapp.dependency.ListingViewScope;
import com.example.wen0m.sampleapp.listing.ListingAdapter;
import com.example.wen0m.sampleapp.listing.ListingPresenter;
import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.Constans;
import com.example.wen0m.sampleapp.utils.SharedPrefsUtil;
import com.example.wen0m.sampleapp.utils.NetworkUtils;
import java.util.ArrayList;
import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class ListingModule {
    @Provides
    @ListingViewScope
    ListingPresenter provideListingPresentor(SharedPrefsUtil sharedPrefs, NetworkUtils utils, CompositeSubscription subscription) {
        return new ListingPresenter(sharedPrefs, utils, subscription);
    }

    @Provides
    ListingAdapter provideListingAdapter() {
        return new ListingAdapter(new ArrayList<Building>(Constans.ELEMENTS_CNT));
    }

}
