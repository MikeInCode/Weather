package mike.weather.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.local.DbHelper;
import mike.weather.data.remote.ApiHelper;
import mike.weather.data.remote.WeatherApi;
import mike.weather.ui.search.SearchActivityPresenter;

@Singleton
public class AppDataManager implements DataManager {

    private ApiHelper apiHelper;
    private DbHelper dbHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper, DbHelper dbHelper) {
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
    }

    @Override
    public void getSuggestedCitiesList(String searchingQuery, SearchActivityPresenter.SearchCallback callback) {
        apiHelper.makeCitySearchQuery(searchingQuery, new SearchActivityPresenter.SearchCallback() {
            @Override
            public void onSuccess(List<WeatherApi.SearchCity> suggestedList) {
                callback.onSuccess(suggestedList);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void addCityToDb(WeatherApi.SearchCity searchCity) {
        dbHelper.insertCity(searchCity);
    }
}
