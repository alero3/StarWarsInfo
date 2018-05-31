package com.example.arota.starwarsinfo.main.list_starships;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.arota.starwarsinfo.main.GenericStarWarsListAdapter;
import com.example.arota.starwarsinfo.main.ItemClickListener;
import com.example.arota.starwarsinfo.main.list_people.PeopleListAdapter;
import com.example.arota.starwarsinfo.main.models.Planet;
import com.example.arota.starwarsinfo.main.models.Starship;

import java.util.List;

public class StarshipsListAdapter extends GenericStarWarsListAdapter<Starship> {

    public static final String TAG = "PeopleListAdapter";
    private OnStarshipDetailListener mCallback;
    private Context context;
    private Activity activity;

    public StarshipsListAdapter(List<Starship> myDataset, Context context) {
        super(myDataset);

        this.context = context;

        if (context instanceof Activity){
            activity =(Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                mCallback = (OnStarshipDetailListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnStarshipDetailListener");
            }
        }
    }

    // Container Activity must implement this interface
    public interface OnStarshipDetailListener {
        void onStarshipDetailSelected(Starship starship);
    }

    @Override
    protected void bindView(Starship item, GenericStarWarsListAdapter.ViewHolder viewHolder) {

        if (item != null)
        {
            viewHolder.getNameTextView().setText(item.getName());

            viewHolder.setItemClickListener(new ItemClickListener<Starship>() {
                @Override
                public void onClick(View view, int position, Starship item) {
                    Log.d(TAG, "Clicked item " + position);
                    mCallback.onStarshipDetailSelected(item);
                }
            });
        }

    }
}
