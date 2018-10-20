package mike.weather.data;

import io.reactivex.Observable;
import io.reactivex.Single;
import mike.weather.data.model.City;
import mike.weather.data.model.ConditionsResponse;
import mike.weather.data.model.SearchResponse;

public interface DataManager {
    Single<SearchResponse> getCitySearchResponse(String query);
    void addCityToDb(City cityToAdd);
    Observable<City> getCitiesFromDb();
    Single<ConditionsResponse> getCityConditionsResponse(String cityQuery);
    void deleteCityFromDb(City cityToDelete);
}
