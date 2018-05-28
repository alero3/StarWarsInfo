package com.example.arota.starwarsinfo.main.mainmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.APIClient;
import com.example.arota.starwarsinfo.main.APIInterface;
import com.example.arota.starwarsinfo.main.Models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.Models.Person;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private List<Person> people;
    private TextView provaTextView;
    private APIInterface apiInterface;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_frag, container, false);

        provaTextView = (TextView) view.findViewById(R.id.prova_tv);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        sendHTTPRequest();

        return view;
    }

    void sendHTTPRequest () {

        Call<PeopleWrapper> call = apiInterface.doGetPeople();

        call.enqueue(new Callback<PeopleWrapper>() {
            @Override
            public void onResponse(Call<PeopleWrapper> call, Response<PeopleWrapper> response) {

                Log.d(TAG,"Response code: " + response.code() + "");

                people = new ArrayList<>();

                for (Person p : response.body().getPeople())
                    people.add(p);

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
