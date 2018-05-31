package com.example.arota.starwarsinfo.main.list_planets;

import android.util.Log;

import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.PlanetsWrapper;
import com.example.arota.starwarsinfo.main.retrofit.APIClient;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarWarsPlanetsListPresenter implements StarWarsPlanetsListContract.Presenter {

    private List<Planet> planets;
    Integer i = 1;
    APIInterface apiInterface;
    StarWarsPlanetsListContract.View mPlanetsView;
    private static final String TAG = "Planets List Presenter";


    public StarWarsPlanetsListPresenter(StarWarsPlanetsListContract.View mPlanetsView) {

        this.mPlanetsView = mPlanetsView;

        apiInterface = APIClient.getClient().create(APIInterface.class);
        planets = new ArrayList<>();


        mPlanetsView.setPresenter(this);

    }
    @Override
    public void downloadPlanets() {

        Call<PlanetsWrapper> call = apiInterface.doGetPlanetsAtPage(i.toString());


        call.enqueue(new Callback<PlanetsWrapper>() {
            @Override
            public void onResponse(Call<PlanetsWrapper> call, Response<PlanetsWrapper> response) {

                Log.d(TAG,"Response code: " + response.code() + "");


                for (Planet p : response.body().getPlanets())
                    planets.add(p);

                if (response.body().getNext() != null)
                {
                    i++;
                    downloadPlanets();
                }

                else
                {
                    mPlanetsView.updateList(planets);
                }



                return;

            }

            @Override
            public void onFailure(Call<PlanetsWrapper> call, Throwable t) {

                Log.e(TAG,"HTTP request failed");
                call.cancel();

            }
        });

    }

    @Override
    public void start() {

    }
}
