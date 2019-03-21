package com.example.searchviewandrecyclerview;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PlanetService {

    @GET("JDVila/storybook/master/planets.json")
    Single<PlanetResponse> getPlanets();


}
