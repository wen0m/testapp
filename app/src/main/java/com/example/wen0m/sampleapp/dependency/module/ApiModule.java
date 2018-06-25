package com.example.wen0m.sampleapp.dependency.module;

import com.example.wen0m.sampleapp.shared.ApiHotelEndpoint;
import com.example.wen0m.sampleapp.shared.ApiUfcEndpoint;
import com.example.wen0m.sampleapp.shared.BuildingsApiService;
import com.example.wen0m.sampleapp.shared.UfcApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moisha on 31.01.2018.
 */

@Module(includes = WebServiceModule.class)
public class ApiModule {
    @Provides
    BuildingsApiService provideApiService(ApiHotelEndpoint api) {
        return new BuildingsApiService(api);
    }

    @Provides
    UfcApiService provideUfcApiService(ApiUfcEndpoint api) {
        return new UfcApiService(api);
    }
}
