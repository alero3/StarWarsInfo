package com.example.arota.starwarsinfo.main.mainmenu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.list.StarWarsListFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnInfoTypeListener {

    private final String PLANETS_ID = "PLANETS";
    private final String PEOPLE_ID = "PEOPLE";
    private final String STARSHIPS_ID = "STARSHIPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.frag_placeholder, mainFragment)
                .commit();

    }


    @Override
    public void onInfoTypeSelected(int buttonId) {

        Log.d("MainActivity", "Event received!");

        StarWarsListFragment starWarsListFragment;
        FragmentManager fm = getSupportFragmentManager();

        switch (buttonId)
        {
            case R.id.people_btn:
                starWarsListFragment = StarWarsListFragment.newInstance(PEOPLE_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

            case R.id.planets_btn:
                starWarsListFragment = StarWarsListFragment.newInstance(PLANETS_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

            case R.id.starships_btn:
                starWarsListFragment = StarWarsListFragment.newInstance(STARSHIPS_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

                default: break;

        }




    }
}
