package com.example.wen0m.sampleapp.deprecated;


import com.example.wen0m.sampleapp.dependency.DetailedViewScope;
import com.example.wen0m.sampleapp.deprecated.DetailsModule;
import com.example.wen0m.sampleapp.ui.activities.HouseDetailsActivity;

import dagger.Subcomponent;

@Deprecated
@DetailedViewScope
@Subcomponent(modules = DetailsModule.class)
//@Component(dependencies = AppComponent.class, modules = DetailsModule.class)
public interface DetailedComponent {
    void inject(HouseDetailsActivity activity);
}
