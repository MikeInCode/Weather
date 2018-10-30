package mike.weather.data;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import mike.weather.data.model.ApiResponse;
import mike.weather.data.model.City;
import mike.weather.data.model.ConditionsData;
import mike.weather.data.model.ForecastData;
import mike.weather.data.model.SearchData;

public interface DataManager {
    Single<ApiResponse<List<SearchData>>> getCitySearchResponse(String query);
    void addCityToDb(City cityToAdd);
    Observable<City> getCitiesFromDb();
    Single<ApiResponse<ConditionsData>> getCityConditionsResponse(String cityQuery);
    void deleteCityFromDb(City cityToDelete);
    Single<ApiResponse<List<ForecastData>>> getCityForecastResponse(String cityQuery,
                                                                    String timeInterval,
                                                                    String responseSize);
}
