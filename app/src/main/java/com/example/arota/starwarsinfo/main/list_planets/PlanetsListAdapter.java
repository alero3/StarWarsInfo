package com.example.arota.starwarsinfo.main.list_planets;

import android.app.Activity;
import android.content.Context;
import android.drm.DrmStore;
import android.util.Log;
import android.view.View;

import com.example.arota.starwarsinfo.main.GenericStarWarsListAdapter;
import com.example.arota.starwarsinfo.main.ItemClickListener;
import com.example.arota.starwarsinfo.main.list_people.PeopleListAdapter;
import com.example.arota.starwarsinfo.main.models.Planet;

import java.util.List;

public class PlanetsListAdapter extends GenericStarWarsListAdapter<Planet> {

    public static final String TAG = "PlanetListAdapter";
    private Context context;
    private Activity activity;
    private OnPlanetDetailListener mCallback;

    // Container Activity must implement this interface
    public interface OnPlanetDetailListener {
        void onPlanetDetailSelected(Planet planet);
    }


    public PlanetsListAdapter(List<Planet> myDataset, Context context) {
        super(myDataset);

        this.context = context;


        if (context instanceof Activity){
            activity =(Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                mCallback = (OnPlanetDetailListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnPlanetDetailListener");
            }
        }
    }

    @Override
    protected void bindView(Planet item, GenericStarWarsListAdapter.ViewHolder viewHolder) {

        if (item != null)
        {
            viewHolder.getNameTextView().setText(item.getName());

            viewHolder.setItemClickListener(new ItemClickListener<Planet>() {
                @Override
                public void onClick(View view, int position, Planet item) {
                    Log.d(TAG, "Clicked item " + position);
                    mCallback.onPlanetDetailSelected(item);
                }
            });
        }


    }
}
