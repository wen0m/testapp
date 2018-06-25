package com.example.wen0m.sampleapp.shared;

import com.example.wen0m.sampleapp.mvp.models.Fighter;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by moisha on 01.02.2018.
 */

public interface ApiUfcEndpoint {

    String BASE_URL = "http://ufc-data-api.ufc.com/api/v3/iphone/fighters/";

    @GET(".")
    Observable<List<Fighter>> getFightersList();

    @GET("{fighter_id}.json")
    Observable<Fighter> getFighterDetailed(@Path("fighter_id") long id);
}
