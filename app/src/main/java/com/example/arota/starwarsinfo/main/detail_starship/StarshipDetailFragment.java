package com.example.arota.starwarsinfo.main.detail_starship;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arota.starwarsinfo.R;
import com.example.arota.starwarsinfo.main.models.Person;
import com.example.arota.starwarsinfo.main.models.Starship;

public class StarshipDetailFragment extends Fragment {

    Starship starship;
    public static final String ITEM = "item";



    public static StarshipDetailFragment newInstance (Starship item)
    {
        StarshipDetailFragment frag = new StarshipDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ITEM, item);
        frag.setArguments(args);
        return frag;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            starship = args.getParcelable(ITEM);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item_detail_frag, container, false);
        TextView nameDetailTextView = view.findViewById(R.id.name_detail_tv);
        TextView paramOneNameTextView = view.findViewById(R.id.param_one_name_tv);
        TextView paramOneValueTextView = view.findViewById(R.id.param_one_value_tv);
        TextView paramTwoNameTextView = view.findViewById(R.id.param_two_name_tv);
        TextView paramTwoValueTextView = view.findViewById(R.id.param_two_value_tv);
        TextView paramThreeNameTextView = view.findViewById(R.id.param_three_name_tv);
        TextView paramThreeValueTextView = view.findViewById(R.id.param_three_value_tv);
        TextView paramFourNameTextView = view.findViewById(R.id.param_four_name_tv);
        TextView paramFourValueTextView = view.findViewById(R.id.param_four_value_tv);

        nameDetailTextView.setText(starship.getName());
        paramOneNameTextView.setText("Model:");
        paramOneValueTextView.setText(starship.getModel());
        paramTwoNameTextView.setText("Manufacturer:");
        paramTwoValueTextView.setText(starship.getManufacturer());
        paramThreeNameTextView.setText("Cost in credits:");
        paramThreeValueTextView.setText(starship.getCostInCredits());
        paramFourNameTextView.setText("Length:");
        paramFourValueTextView.setText(starship.getLength());


        return view;

    }
}
