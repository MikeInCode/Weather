package mike.weather.data.remote;

import mike.weather.ui.search.SearchActivityPresenter;

public interface ApiHelper {
    void makeCitySearchQuery(String searchingQuery, SearchActivityPresenter.SearchCallback callback);
}
