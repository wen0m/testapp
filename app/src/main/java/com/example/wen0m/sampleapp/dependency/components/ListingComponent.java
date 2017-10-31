package com.example.wen0m.sampleapp.dependency.components;


import com.example.wen0m.sampleapp.dependency.ListingViewScope;
import com.example.wen0m.sampleapp.dependency.module.ListingModule;
import com.example.wen0m.sampleapp.listing.HouseListingActivity;
import com.example.wen0m.sampleapp.listing.sorting.SortingDialog;

import dagger.Subcomponent;


@ListingViewScope
@Subcomponent(modules = ListingModule.class)
//@Component(dependencies = AppComponent.class, modules = ListingModule.class)
public interface ListingComponent {
    void inject(HouseListingActivity activity);
    void inject(SortingDialog dialog);
}
