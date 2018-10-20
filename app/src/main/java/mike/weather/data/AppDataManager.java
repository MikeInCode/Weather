package mike.weather.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import mike.weather.data.local.DbHelper;
import mike.weather.data.model.City;
import mike.weather.data.model.ConditionsResponse;
import mike.weather.data.model.SearchResponse;
import mike.weather.data.remote.ApiHelper;

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
    public Single<SearchResponse> getCitySearchResponse(String query) {
        return apiHelper.makeCitySearchRequest(query);
    }

    @Override
    public void addCityToDb(City cityToAdd) {
        dbHelper.insertCity(cityToAdd);
    }

    @Override
    public Observable<City> getCitiesFromDb() {
        return dbHelper.readAllCities();
    }

    @Override
    public Single<ConditionsResponse> getCityConditionsResponse(String cityQuery) {
        return apiHelper.makeCurrentConditionsRequest(cityQuery);
    }

    @Override
    public void deleteCityFromDb(City cityToDelete) {
        dbHelper.deleteCity(cityToDelete);
    }
}
