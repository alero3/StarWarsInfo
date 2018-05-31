package com.example.arota.starwarsinfo.main.detail_person;

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


public class PersonDetailFragment extends Fragment {

    Person person;
    public static final String ITEM = "item";



    public static PersonDetailFragment newInstance (Person item)
    {
        PersonDetailFragment frag = new PersonDetailFragment();
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
            person = args.getParcelable(ITEM);
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

        nameDetailTextView.setText(person.getName());
        paramOneNameTextView.setText("Height:");
        paramOneValueTextView.setText(person.getHeight());
        paramTwoNameTextView.setText("Mass:");
        paramTwoValueTextView.setText(person.getMass());
        paramThreeNameTextView.setText("Hair Color:");
        paramThreeValueTextView.setText(person.getHairColor());
        paramFourNameTextView.setText("Skin Color:");
        paramFourValueTextView.setText(person.getSkinColor());


        return view;

    }
}
