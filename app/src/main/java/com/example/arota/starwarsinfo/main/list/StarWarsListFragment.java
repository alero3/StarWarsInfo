package com.example.arota.starwarsinfo.main.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.retrofit.APIClient;
import com.example.arota.starwarsinfo.main.retrofit.APIInterface;

import java.util.List;

public class StarWarsListFragment extends Fragment implements StarWarsListContract.View {

    String infoType;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private StarWarsListContract.Presenter mPresenter;
    private View progress;
    private APIInterface apiInterface;



    private String[] myDataset = {"Luke", "Obi Wan Kenobi", "Leila"};

    @Override
    public void setPresenter(StarWarsListContract.Presenter presenter) {

        mPresenter = presenter;

    }

    public static StarWarsListFragment newInstance (String infoType)
    {
        StarWarsListFragment frag = new StarWarsListFragment();
        Bundle args = new Bundle();
        args.putString("dataType", infoType);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);


        Bundle args = getArguments();
        if (args != null) {
            infoType = args.getString("dataType");
        }

        mPresenter = new StarWarsListPresenter(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.list_frag, container, false);
        TextView titleTextView = view.findViewById(R.id.title_tv);
        progress = view.findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);

        titleTextView.setText(infoType);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Download data from swapi.co
        mPresenter.sendHTTPRequest(apiInterface);

        /*
        mAdapter = new StarWarsListAdapter(myDataset);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(mAdapter);
        */

        return view;

    }

    @Override
    public void updateList(List<Person> updatedList) {

        mAdapter = new StarWarsListAdapter(updatedList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        progress.setVisibility(View.GONE);



    }
}
