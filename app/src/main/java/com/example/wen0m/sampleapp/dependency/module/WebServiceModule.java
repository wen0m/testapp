package com.example.wen0m.sampleapp.dependency.module;

import com.example.wen0m.sampleapp.shared.ApiHotelEndpoint;
import com.example.wen0m.sampleapp.shared.ApiUfcEndpoint;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module(includes = NetworkModule.class)
public class WebServiceModule {
    @Provides
    @Singleton
    ApiHotelEndpoint houseWebService(Retrofit retrofit) {
        return retrofit.create(ApiHotelEndpoint.class);
    }

    @Provides
    @Singleton
    ApiUfcEndpoint fightersWebService(Retrofit retrofit) {
        return retrofit.create(ApiUfcEndpoint.class);
    }
}
