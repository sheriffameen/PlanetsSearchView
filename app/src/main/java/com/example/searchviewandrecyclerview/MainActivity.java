package com.example.searchviewandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<PlanetResponse>, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private List<Planet> planets;
    private PlanetAdapter planetAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.planetsRecycler_view);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        getPlanets();
    }


    public void getPlanets(){
        Call<PlanetResponse> planetResponseCall = PlanetClient.getInstance().getPlanetResponse();
        planetResponseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<PlanetResponse> call, Response<PlanetResponse> response) {
        PlanetResponse planetResponse = response.body();
         planets = planetResponse.getPlanets();

         planetAdapter = new PlanetAdapter(planets);

        recyclerView.setAdapter(planetAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }

    @Override
    public void onFailure(Call<PlanetResponse> call, Throwable t) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Planet> newPlanetList = new ArrayList<>();
        for (Planet planet : planets) {
            if (planet.getName().toLowerCase().startsWith(s.toLowerCase())){
                newPlanetList.add(planet);
            }
        }
        planetAdapter.setData(newPlanetList);
        return false;
    }
}
