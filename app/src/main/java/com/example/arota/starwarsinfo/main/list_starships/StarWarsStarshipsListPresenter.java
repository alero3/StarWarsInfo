package com.example.arota.starwarsinfo.main.list_starships;

import android.util.Log;

import com.example.arota.starwarsinfo.main.list_planets.StarWarsPlanetsListContract;
import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.PlanetsWrapper;
import com.example.arota.starwarsinfo.main.models.Starship;
import com.example.arota.starwarsinfo.main.models.StarshipsWrapper;
import com.example.arota.starwarsinfo.main.retrofit.APIClient;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarWarsStarshipsListPresenter implements StarWarsStarshipsListContract.Presenter {

    private List<Starship> starships;
    Integer i = 1;
    APIInterface apiInterface;
    StarWarsStarshipsListContract.View mStarshipsView;
    private static final String TAG = "Planets List Presenter";


    public StarWarsStarshipsListPresenter(StarWarsStarshipsListContract.View mStarshipsView) {

        this.mStarshipsView = mStarshipsView;

        apiInterface = APIClient.getClient().create(APIInterface.class);
        starships = new ArrayList<>();


        mStarshipsView.setPresenter(this);

    }
    @Override
    public void downloadStarships() {

        Call<StarshipsWrapper> call = apiInterface.doGetStarshipsAtPage(i.toString());


        call.enqueue(new Callback<StarshipsWrapper>() {
            @Override
            public void onResponse(Call<StarshipsWrapper> call, Response<StarshipsWrapper> response) {

                Log.d(TAG,"Response code: " + response.code() + "");


                for (Starship s : response.body().getStarships())
                    starships.add(s);

                if (response.body().getNext() != null)
                {
                    i++;
                    downloadStarships();
                }

                else
                {
                    mStarshipsView.updateList(starships);
                }



                return;

            }

            @Override
            public void onFailure(Call<StarshipsWrapper> call, Throwable t) {

                Log.e(TAG,"HTTP request failed");
                call.cancel();

            }
        });

    }

    @Override
    public void start() {

    }
}
