package com.example.wen0m.sampleapp.dependency.module;


import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.ApiHotelEndpoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import java.lang.reflect.Type;
import java.util.List;

@Module(includes = AppModule.class)
public class NetworkModule {
    private static Type listType = new TypeToken<List<Building>>(){}.getType();

    private static class CustomDeserializer implements JsonDeserializer<List<Building>> {
        @Override
        public List<Building> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonElement res = json.getAsJsonObject().get("data").getAsJsonObject().get("results");
            return new Gson().fromJson(res, listType);
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new okhttp3.OkHttpClient.Builder()
//                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit getClient(OkHttpClient client) {
        return new Retrofit.Builder()
                    .baseUrl(ApiHotelEndpoint.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder()
                                    .setLenient()
                                    .registerTypeAdapter(listType, new CustomDeserializer())
                                    .create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(setSSLFactoryForClient(client))
                    .build();
    }

    @Provides
    @Singleton
    ApiHotelEndpoint houseWebService(Retrofit retrofit) {
        return retrofit.create(ApiHotelEndpoint.class);
    }
}
