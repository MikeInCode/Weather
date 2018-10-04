package mike.weather.data;

import mike.weather.data.model.SearchCity;
import mike.weather.ui.main.MainActivityPresenter;
import mike.weather.ui.search.SearchActivityPresenter;

public interface DataManager {
    void getSuggestedCitiesList(String searchingQuery, SearchActivityPresenter.Callback callback);
    void addCityToDb(SearchCity searchCity);
    void getMainCitiesList(MainActivityPresenter.Callback callback);
}
