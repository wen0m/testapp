package com.example.wen0m.sampleapp.shared;

import com.example.wen0m.sampleapp.models.Building;
import com.example.wen0m.sampleapp.shared.Constans;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ApiHotelEndpoint {
    String BASE_URL = "http://api.trend-dev.ru/v2/apartments/blocks/";

    @GET("search?show_type=list&count=" + Constans.ELEMENTS_CNT)
    Observable<List<Building>> getHotelsList();

    @GET("{hotel_id}.json")
    Observable<Building> getHotelDetailed(@Path("hotel_id") long id);
}
