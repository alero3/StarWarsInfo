package com.example.arota.starwarsinfo.main.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.models.Person;

import java.util.List;

public class StarWarsListAdapter extends RecyclerView.Adapter<StarWarsListAdapter.ViewHolder> {

    private List<Person> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        private TextView nameTextView;

        public ViewHolder(View v) {
            super(v);

            nameTextView = v.findViewById(R.id.name_tv);

        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StarWarsListAdapter(List<Person> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.nameTextView.setText(mDataset.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }




}
