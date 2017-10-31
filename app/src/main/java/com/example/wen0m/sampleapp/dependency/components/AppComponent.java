package com.example.wen0m.sampleapp.dependency.components;


import com.example.wen0m.sampleapp.dependency.module.AppModule;
import com.example.wen0m.sampleapp.dependency.module.DetailsModule;
import com.example.wen0m.sampleapp.dependency.module.ListingModule;
import com.example.wen0m.sampleapp.dependency.module.NetworkModule;
import com.example.wen0m.sampleapp.dependency.module.SortingModule;
import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = { AppModule.class, SortingModule.class, NetworkModule.class})
public interface AppComponent {

    DetailedComponent add(DetailsModule module);

    ListingComponent add(ListingModule module);
}
