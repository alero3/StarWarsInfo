package com.example.arota.starwarsinfo.main.list_starships;

import com.example.arota.starwarsinfo.main.BasePresenter;
import com.example.arota.starwarsinfo.main.BaseView;
import com.example.arota.starwarsinfo.main.models.Starship;

import java.util.List;

public interface StarWarsStarshipsListContract {

    public interface Presenter extends BasePresenter {

        void downloadStarships();

    }
    public interface View extends BaseView<StarWarsStarshipsListContract.Presenter> {

        public void setPresenter(StarWarsStarshipsListContract.Presenter presenter);
        public void updateList(List<Starship> updatedList);

    }
}
