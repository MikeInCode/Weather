package mike.weather.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.local.DbHelper;
import mike.weather.data.model.MainCity;
import mike.weather.data.remote.ApiHelper;
import mike.weather.data.model.SearchCity;
import mike.weather.data.model.CurrentConditions;
import mike.weather.ui.main.MainActivityPresenter;
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
    public void getSuggestedCitiesList(String searchingQuery, SearchActivityPresenter.Callback callback) {
        apiHelper.makeCitySearchQuery(searchingQuery, new SearchActivityPresenter.Callback() {
            @Override
            public void onSuccess(List<SearchCity> suggestedList) {
                callback.onSuccess(suggestedList);
            }

            @Override
            public void onServerError() {
                callback.onServerError();
            }

            @Override
            public void onInternetError() {
                callback.onInternetError();
            }
        });
    }

    @Override
    public void addCityToDb(SearchCity searchCity) {
        dbHelper.insertCity(searchCity);
    }

    @Override
    public void getMainCitiesList(MainActivityPresenter.Callback callback) {
        List<MainCity> mainCitiesList = dbHelper.readAllCities();
        for (MainCity city : mainCitiesList) {
            apiHelper.makeCurrentConditionsQuery(city.getKey(), new MainActivityPresenter.Callback() {
                @Override
                public void onSuccess(List<CurrentConditions> currentConditions, List<MainCity> citiesList) {
                    city.setTemperature(currentConditions.get(0).getTemperature().getMetric().getDegrees());
                    callback.onSuccess(null, mainCitiesList);
                }

                @Override
                public void onServerError(List<MainCity> citiesList) {
                    city.setTemperature("--");
                    callback.onServerError(mainCitiesList);
                }

                @Override
                public void onInternetError(List<MainCity> citiesList) {
                    city.setTemperature("--");
                    callback.onInternetError(mainCitiesList);
                }
            });
        }
    }
}
