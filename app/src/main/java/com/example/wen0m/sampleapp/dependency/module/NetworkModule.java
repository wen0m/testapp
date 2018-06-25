package com.example.wen0m.sampleapp.dependency.module;


import android.content.Context;
import com.example.wen0m.sampleapp.deprecated.Building;
import com.example.wen0m.sampleapp.shared.ApiUfcEndpoint;
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
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import java.lang.reflect.Type;
import java.util.List;


@Module(includes = AppModule.class)
public class NetworkModule {
    private static Type listType = new TypeToken<List<Building>>(){}.getType();

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        return new Cache(context.getCacheDir(), 10 * 1024 * 1024);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        return new okhttp3.OkHttpClient.Builder()
                .cache(cache)
//                .connectTimeout(CONNECT_TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit getClient(OkHttpClient client) {
        return new Retrofit.Builder()
                    .baseUrl(ApiUfcEndpoint.BASE_URL)
                    .client(client)
//                    .client(setSSLFactoryForClient(client))
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder()
                                    .setLenient()
//                                    .registerTypeAdapter(listType, new CustomDeserializer())
                                    .create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
    }


    private static class CustomDeserializer implements JsonDeserializer<List<Building>> {
        @Override
        public List<Building> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonElement res = json.getAsJsonObject().get("data").getAsJsonObject().get("results");
            return new Gson().fromJson(res, listType);
        }
    }
}
