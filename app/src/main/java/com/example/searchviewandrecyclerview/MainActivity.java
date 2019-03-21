package com.example.searchviewandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private List<Planet> planets;
    private PlanetAdapter planetAdapter;
    private SearchView searchView;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.planetsRecycler_view);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);
        compositeDisposable = new CompositeDisposable();

        getPlanets();
    }


    public void getPlanets(){
        Disposable disposable = PlanetClient.getInstance()
                .getPlanetResponse().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> response.getPlanets())
                .subscribe(planets -> {
                    this.planets = planets;
                    planetAdapter = new PlanetAdapter(planets);

                    recyclerView.setAdapter(planetAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                },error ->error.printStackTrace());

        compositeDisposable.add(disposable);
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
