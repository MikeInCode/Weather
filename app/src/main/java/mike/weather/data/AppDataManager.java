package mike.weather.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.local.DbHelper;
import mike.weather.data.local.PreferencesHelper;
import mike.weather.data.model.City;
import mike.weather.data.remote.ApiHelper;
import mike.weather.ui.main.MainActivityPresenter;
import mike.weather.ui.search.SearchActivityPresenter;

@Singleton
public class AppDataManager implements DataManager {

    private ApiHelper apiHelper;
    private DbHelper dbHelper;
    private PreferencesHelper preferencesHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public void getSuggestedCitiesList(String searchingPhrase, SearchActivityPresenter.Callback callback) {
        apiHelper.makeCitySearchQuery(searchingPhrase, new SearchActivityPresenter.Callback() {
            @Override
            public void onSuccess(List<City> suggestedList) {
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
    public boolean addCityToDb(City cityToAdd) {
        return dbHelper.insertCity(cityToAdd);
    }

    @Override
    public void getMainCitiesList(MainActivityPresenter.Callback callback) {
        List<City> mainCitiesList = dbHelper.readAllCities();
        String citiesIds = buildCitiesIds(mainCitiesList);
        apiHelper.makeCurrentConditionsQuery(citiesIds, preferencesHelper.getBaseUnits(),
                new MainActivityPresenter.Callback() {
                    @Override
                    public void onSuccess(List<City> updatedCitiesList) {
                        callback.onSuccess(updatedCitiesList);
                    }

                    @Override
                    public void onServerError(List<City> oldCitiesList) {
                        callback.onServerError(mainCitiesList);
                    }

                    @Override
                    public void onInternetError(List<City> oldCitiesList) {
                        callback.onInternetError(mainCitiesList);
                    }
                });
    }

    private String buildCitiesIds(List<City> citiesList) {
        StringBuilder citiesIds = new StringBuilder();
        for (int i = 0; i < citiesList.size(); i++) {
            citiesIds.append(citiesList.get(i).getId());
            if (i < citiesList.size() - 1) {
                citiesIds.append(",");
            }
        }
        return citiesIds.toString();
    }

    @Override
    public boolean getUnitsSwitcherState() {
        return !preferencesHelper.getBaseUnits().equals("metric");
     }

    @Override
    public void changeUnitsPreference() {
        if (preferencesHelper.getBaseUnits().equals("metric")) {
            preferencesHelper.setBaseUnits("imperial");
        } else {
            preferencesHelper.setBaseUnits("metric");
        }
    }
}
