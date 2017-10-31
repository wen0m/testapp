package com.example.wen0m.sampleapp.dependency.components;


import com.example.wen0m.sampleapp.dependency.DetailedViewScope;
import com.example.wen0m.sampleapp.dependency.module.DetailsModule;
import com.example.wen0m.sampleapp.detailed.HouseDetailsActivity;
import dagger.Component;
import dagger.Subcomponent;


@DetailedViewScope
@Subcomponent(modules = DetailsModule.class)
//@Component(dependencies = AppComponent.class, modules = DetailsModule.class)
public interface DetailedComponent {
    void inject(HouseDetailsActivity activity);
}
