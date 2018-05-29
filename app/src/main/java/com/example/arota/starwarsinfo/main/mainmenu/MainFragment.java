package com.example.arota.starwarsinfo.main.mainmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.retrofit.APIClient;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;
import com.example.arota.starwarsinfo.main.models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.models.Person;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MainFragment";
    private List<Person> people;
    private Button peopleButton;
    private APIInterface apiInterface;
    private OnInfoTypeListener mCallback;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.people_btn:
                mCallback.onInfoTypeSelected(v.getId());
                break;

            case R.id.planets_btn:
                mCallback.onInfoTypeSelected(v.getId());
                break;

            case R.id.starships_btn:
                mCallback.onInfoTypeSelected(v.getId());
                break;

            default:
                break;
        }

    }


    // Container Activity must implement this interface
    public interface OnInfoTypeListener {
        void onInfoTypeSelected(int buttonId);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity =(Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                mCallback = (OnInfoTypeListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        apiInterface = APIClient.getClient().create(APIInterface.class);

        View view = inflater.inflate(R.layout.main_frag, container, false);
        Button peopleButton = (Button) view.findViewById(R.id.people_btn);
        peopleButton.setOnClickListener(this); // calling onClick() method
        Button planetsButton = (Button) view.findViewById(R.id.planets_btn);
        planetsButton.setOnClickListener(this);
        Button starshipsButton = (Button) view.findViewById(R.id.starships_btn);
        starshipsButton.setOnClickListener(this);


        //sendHTTPRequest();

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
