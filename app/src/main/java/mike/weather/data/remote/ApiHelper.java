package mike.weather.data.remote;

import mike.weather.ui.main.MainActivityPresenter;
import mike.weather.ui.search.SearchActivityPresenter;

public interface ApiHelper {
    void makeCitySearchQuery(String searchingPhrase, SearchActivityPresenter.Callback callback);
    void makeCurrentConditionsQuery(String citiesIds, String units, MainActivityPresenter.Callback callback);
}
