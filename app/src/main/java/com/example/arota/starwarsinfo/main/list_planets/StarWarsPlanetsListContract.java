package com.example.arota.starwarsinfo.main.list_planets;

import com.example.arota.starwarsinfo.main.BasePresenter;
import com.example.arota.starwarsinfo.main.BaseView;
import com.example.arota.starwarsinfo.main.models.Planet;

import java.util.List;

public interface StarWarsPlanetsListContract {

    public interface Presenter extends BasePresenter {

        void downloadPlanets();

    }
    public interface View extends BaseView<StarWarsPlanetsListContract.Presenter> {

        public void setPresenter(StarWarsPlanetsListContract.Presenter presenter);
        public void updateList(List<Planet> updatedList);

    }
}
