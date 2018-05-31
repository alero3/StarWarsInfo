package com.example.arota.starwarsinfo.main.list_starships;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.GenericStarWarsListAdapter;
import com.example.arota.starwarsinfo.main.list_planets.PlanetsListAdapter;
import com.example.arota.starwarsinfo.main.list_planets.StarWarsPlanetsListContract;
import com.example.arota.starwarsinfo.main.list_planets.StarWarsPlanetsListFragment;
import com.example.arota.starwarsinfo.main.list_planets.StarWarsPlanetsListPresenter;
import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.Starship;

import java.util.ArrayList;
import java.util.List;

public class StarWarsStarshipsListFragment extends Fragment implements StarWarsStarshipsListContract.View {

    String infoType;
    private RecyclerView mRecyclerView;
    private GenericStarWarsListAdapter<Starship> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private StarWarsStarshipsListContract.Presenter mPresenter;
    private View progress;
    private List<Starship> starshipsList = new ArrayList<>();
    private static final String STARSHIPS_LIST = "starshipsList";





    public static StarWarsStarshipsListFragment newInstance (String infoType)
    {
        StarWarsStarshipsListFragment frag = new StarWarsStarshipsListFragment();
        Bundle args = new Bundle();
        args.putString("dataType", infoType);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            infoType = args.getString("dataType");
        }

        mPresenter = new StarWarsStarshipsListPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.list_frag, container, false);
        TextView titleTextView = view.findViewById(R.id.title_tv);
        progress = view.findViewById(R.id.progress);


        titleTextView.setText(infoType);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StarshipsListAdapter(starshipsList, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        // use a linear layout manager

        if (savedInstanceState == null)
        {
            progress.setVisibility(View.VISIBLE);
            // Download people from swapi.co
            mPresenter.downloadStarships();
        }
        else
        {
            starshipsList = savedInstanceState.getParcelableArrayList(STARSHIPS_LIST);
            mAdapter.setData(starshipsList);
            mAdapter.notifyDataSetChanged();
        }


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putParcelableArrayList(STARSHIPS_LIST, (ArrayList<? extends Parcelable>) starshipsList);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void setPresenter(StarWarsStarshipsListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateList(List<Starship> updatedList) {

        starshipsList = updatedList;
        mAdapter.setData(starshipsList);
        mAdapter.notifyDataSetChanged();
        progress.setVisibility(View.GONE);

    }
}
