package com.example.arota.starwarsinfo.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.mainmenu.MainContract;
import com.example.arota.starwarsinfo.main.models.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericStarWarsListAdapter<T> extends RecyclerView.Adapter<GenericStarWarsListAdapter.ViewHolder> {

    private List<T> mDataset;
    private static final String TAG = "GenericAdapter";





    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;


        public TextView getNameTextView() {
            return nameTextView;
        }

        // each data item is just a string in this case
        private TextView nameTextView;

        public ViewHolder(View v) {
            super(v);

            nameTextView = v.findViewById(R.id.name_tv);
            v.setOnClickListener(this);

        }


        public void setItemClickListener(ItemClickListener listener)
        {
            this.itemClickListener = listener;
        }

        @Override
        public void onClick(View v) {
            T item = mDataset.get(getAdapterPosition());
            itemClickListener.onClick(v, getAdapterPosition(), item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GenericStarWarsListAdapter(List<T> myDataset) {
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

    protected abstract void bindView(T item, GenericStarWarsListAdapter.ViewHolder viewHolder);


    @Override
    public void onBindViewHolder(@NonNull GenericStarWarsListAdapter.ViewHolder holder, int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        bindView(getItem(position), holder);


        //holder.nameTextView.setText(mDataset.get(position).getName());

    }

    public T getItem(int index) {
        return ((mDataset != null && index < mDataset.size()) ? mDataset.get(index) : null);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setData (List<T> peopleList)
    {
        mDataset = peopleList;
    }




}
