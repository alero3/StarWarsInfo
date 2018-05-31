package com.example.arota.starwarsinfo.main.mainmenu;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.detail_person.PersonDetailFragment;
import com.example.arota.starwarsinfo.main.detail_planet.PlanetDetailFragment;
import com.example.arota.starwarsinfo.main.detail_starship.StarshipDetailFragment;
import com.example.arota.starwarsinfo.main.list_people.PeopleListAdapter;
import com.example.arota.starwarsinfo.main.list_people.StarWarsListFragment;
import com.example.arota.starwarsinfo.main.list_planets.PlanetsListAdapter;
import com.example.arota.starwarsinfo.main.list_planets.StarWarsPlanetsListFragment;
import com.example.arota.starwarsinfo.main.list_starships.StarWarsStarshipsListFragment;
import com.example.arota.starwarsinfo.main.list_starships.StarshipsListAdapter;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.Starship;

public class MainActivity extends AppCompatActivity implements MainFragment.OnInfoTypeListener, PeopleListAdapter.OnPersonDetailListener,
    PlanetsListAdapter.OnPlanetDetailListener, StarshipsListAdapter.OnStarshipDetailListener

{

    private final String PLANETS_ID = "PLANETS";
    private final String PEOPLE_ID = "PEOPLE";
    private final String STARSHIPS_ID = "STARSHIPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
        {
            MainFragment mainFragment = new MainFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.frag_placeholder, mainFragment)
                    .commit();
        }


    }


    @Override
    public void onInfoTypeSelected(int buttonId) {

        Log.d("MainActivity", "Event received!");


        FragmentManager fm = getSupportFragmentManager();

        switch (buttonId)
        {
            case R.id.people_btn:
                StarWarsListFragment starWarsListFragment = StarWarsListFragment.newInstance(PEOPLE_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

            case R.id.planets_btn:
                StarWarsPlanetsListFragment starWarsPlanetsListFragment = StarWarsPlanetsListFragment.newInstance(PLANETS_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsPlanetsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

            case R.id.starships_btn:
                StarWarsStarshipsListFragment starWarsStarshipsListFragment = StarWarsStarshipsListFragment.newInstance(STARSHIPS_ID);
                fm.beginTransaction()
                        .replace(R.id.frag_placeholder, starWarsStarshipsListFragment)
                        .addToBackStack("tag")
                        .commit();
                break;

                default: break;

        }




    }


    @Override
    public void onPersonDetailSelected(Person person) {

        Log.d("MainActivity", "Person selected!");

        FragmentManager fm = getSupportFragmentManager();

        PersonDetailFragment personDetailFragment = PersonDetailFragment.newInstance(person);
        fm.beginTransaction()
                .replace(R.id.frag_placeholder, personDetailFragment)
                .addToBackStack("tag")
                .commit();

    }

    @Override
    public void onPlanetDetailSelected(Planet planet) {

        Log.d("MainActivity", "Planet selected!");

        FragmentManager fm = getSupportFragmentManager();

        PlanetDetailFragment planetDetailFragment = PlanetDetailFragment.newInstance(planet);
        fm.beginTransaction()
                .replace(R.id.frag_placeholder, planetDetailFragment)
                .addToBackStack("tag")
                .commit();
    }

    @Override
    public void onStarshipDetailSelected(Starship starship) {

        Log.d("MainActivity", "Starship selected!");

        FragmentManager fm = getSupportFragmentManager();

        StarshipDetailFragment starshipDetailFragment = StarshipDetailFragment.newInstance(starship);
        fm.beginTransaction()
                .replace(R.id.frag_placeholder, starshipDetailFragment)
                .addToBackStack("tag")
                .commit();

    }
}
