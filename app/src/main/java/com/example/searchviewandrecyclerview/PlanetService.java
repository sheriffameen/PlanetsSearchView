package com.example.searchviewandrecyclerview;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlanetService {

    @GET("JDVila/storybook/master/planets.json")
    Call<PlanetResponse>getPlanets();


}
