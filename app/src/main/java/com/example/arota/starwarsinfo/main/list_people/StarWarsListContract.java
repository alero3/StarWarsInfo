package com.example.arota.starwarsinfo.main.list_people;

import com.example.arota.starwarsinfo.main.BasePresenter;
import com.example.arota.starwarsinfo.main.BaseView;
import com.example.arota.starwarsinfo.main.models.Person;

import java.util.List;

public interface StarWarsListContract {

    public interface Presenter extends BasePresenter {

        void downloadPeople();

    }
    public interface View extends BaseView<StarWarsListContract.Presenter> {

        public void setPresenter(StarWarsListContract.Presenter presenter);
        public void updateList(List<Person> updatedList);

    }
}
