package mike.weather.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import mike.weather.data.local.IDbHelper;
import mike.weather.data.model.ApiResponse;
import mike.weather.data.model.City;
import mike.weather.data.model.ConditionsData;
import mike.weather.data.model.ForecastData;
import mike.weather.data.model.SearchData;
import mike.weather.data.remote.IApiHelper;

@Singleton
public class DataManager implements IDataManager {
    private IApiHelper apiHelper;
    private IDbHelper dbHelper;

    @Inject
    public DataManager(IApiHelper apiHelper, IDbHelper dbHelper) {
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
    }

    @Override
    public Single<ApiResponse<List<SearchData>>> getCitySearchResponse(String query) {
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
    public Single<ApiResponse<ConditionsData>> getCityConditionsResponse(String cityQuery) {
        return apiHelper.makeCurrentConditionsRequest(cityQuery);
    }

    @Override
    public void deleteCityFromDb(City cityToDelete) {
        dbHelper.deleteCity(cityToDelete);
    }

    @Override
    public Single<ApiResponse<List<ForecastData>>> getCityForecastResponse(String cityQuery,
                                                                           String timeInterval,
                                                                           String responseSize) {
        return apiHelper.makeForecastRequest(cityQuery, timeInterval, responseSize);
    }
}
