package com.example.arota.starwarsinfo.main.list;

import android.util.Log;
import android.widget.Button;

import com.example.arota.starwarsinfo.main.models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarWarsListPresenter implements StarWarsListContract.Presenter {

    private static final String TAG = "MainFragment";
    private List<Person> people;

    @Override
    public void start() {

    }

    StarWarsListContract.View mProvaView;

    public StarWarsListPresenter(StarWarsListContract.View mProvaView) {
        this.mProvaView = mProvaView;

        mProvaView.setPresenter(this);

    }



    @Override
    public void sendHTTPRequest(APIInterface apiInterface) {

        Call<PeopleWrapper> call = apiInterface.doGetPeople();

        call.enqueue(new Callback<PeopleWrapper>() {
            @Override
            public void onResponse(Call<PeopleWrapper> call, Response<PeopleWrapper> response) {

                Log.d(TAG,"Response code: " + response.code() + "");

                people = new ArrayList<>();

                for (Person p : response.body().getPeople())
                    people.add(p);

                mProvaView.updateList(people);

                return;

            }

            @Override
            public void onFailure(Call<PeopleWrapper> call, Throwable t) {

                Log.e(TAG,"HTTP request failed");
                call.cancel();

            }
        });

    }
}
