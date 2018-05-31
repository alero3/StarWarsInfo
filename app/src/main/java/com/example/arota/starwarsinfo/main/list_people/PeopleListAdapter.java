package com.example.arota.starwarsinfo.main.list_people;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.arota.starwarsinfo.main.GenericStarWarsListAdapter;
import com.example.arota.starwarsinfo.main.ItemClickListener;
import com.example.arota.starwarsinfo.main.mainmenu.MainFragment;
import com.example.arota.starwarsinfo.main.models.Person;

import java.util.List;

public class PeopleListAdapter extends GenericStarWarsListAdapter<Person> {

    public static final String TAG = "PeopleListAdapter";
    private OnPersonDetailListener mCallback;
    private Context context;
    private Activity activity;


    // Container Activity must implement this interface
    public interface OnPersonDetailListener {
        void onPersonDetailSelected(Person person);
    }




    public PeopleListAdapter(List<Person> myDataset, Context context) {


        super(myDataset);
        this.context = context;


        if (context instanceof Activity){
            activity =(Activity) context;

            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                mCallback = (OnPersonDetailListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnPersonDetailListener");
            }
        }





    }

    @Override
    protected void bindView(Person item, GenericStarWarsListAdapter.ViewHolder viewHolder) {

        if (item != null)
        {
            viewHolder.getNameTextView().setText(item.getName());

            viewHolder.setItemClickListener(new ItemClickListener<Person>() {
                @Override
                public void onClick(View view, int position, Person item) {
                    Log.d(TAG, "Clicked item " + position);
                    mCallback.onPersonDetailSelected(item);
                }
            });



        }

    }
}
