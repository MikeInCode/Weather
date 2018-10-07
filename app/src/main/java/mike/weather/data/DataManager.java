package mike.weather.data;

import mike.weather.data.model.City;
import mike.weather.ui.main.MainActivityPresenter;
import mike.weather.ui.search.SearchActivityPresenter;

public interface DataManager {
    void getSuggestedCitiesList(String searchingPhrase, SearchActivityPresenter.Callback callback);
    boolean addCityToDb(City cityToAdd);
    void getMainCitiesList(MainActivityPresenter.Callback callback);
}
