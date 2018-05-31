package com.example.arota.starwarsinfo.main.list_people;

import android.util.Log;

import com.example.arota.starwarsinfo.main.models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.retrofit.APIClient;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarWarsListPresenter implements StarWarsListContract.Presenter {

    private static final String TAG = "StarWarsListPresenter";
    private List<Person> people;
    Integer i = 1;
    APIInterface apiInterface;
    StarWarsListContract.View mPeopleView;


    @Override
    public void start() {

    }


    public StarWarsListPresenter(StarWarsListContract.View mProvaView) {
        this.mPeopleView = mProvaView;

        apiInterface = APIClient.getClient().create(APIInterface.class);
        people = new ArrayList<>();



        mProvaView.setPresenter(this);

    }



    @Override
    public void downloadPeople() {


        Call<PeopleWrapper> call = apiInterface.doGetPeopleAtPage(i.toString());


        call.enqueue(new Callback<PeopleWrapper>() {
            @Override
            public void onResponse(Call<PeopleWrapper> call, Response<PeopleWrapper> response) {

                Log.d(TAG,"Response code: " + response.code() + "");


                for (Person p : response.body().getPeople())
                    people.add(p);

                if (response.body().getNext() != null)
                {
                    i++;
                    downloadPeople();
                }

                else
                {
                    mPeopleView.updateList(people);
                }



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
