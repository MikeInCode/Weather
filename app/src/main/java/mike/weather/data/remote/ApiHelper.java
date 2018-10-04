package mike.weather.data.remote;

import mike.weather.ui.main.MainActivityPresenter;
import mike.weather.ui.search.SearchActivityPresenter;

public interface ApiHelper {
    void makeCitySearchQuery(String searchingQuery, SearchActivityPresenter.Callback callback);
    void makeCurrentConditionsQuery(String cityKey, MainActivityPresenter.Callback callback);
}
