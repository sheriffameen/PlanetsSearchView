package com.example.searchviewandrecyclerview;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlanetClient {
    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static PlanetClient instance;
    private Retrofit retrofit;



    public static PlanetClient getInstance(){
        if (instance == null){
            instance = new PlanetClient();
        }
        return instance;
    }

    private PlanetClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    private PlanetService getPlanetService(){
        return retrofit.create(PlanetService.class);
    }

    public Single<PlanetResponse> getPlanetResponse(){
        return getPlanetService().getPlanets();
    }




}
