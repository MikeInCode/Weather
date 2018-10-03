package mike.weather.data;

import mike.weather.data.remote.WeatherApi;
import mike.weather.ui.search.SearchActivityPresenter;

public interface DataManager {
    void getSuggestedCitiesList(String searchingQuery, SearchActivityPresenter.SearchCallback callback);
    void addCityToDb(WeatherApi.SearchCity searchCity);
}
