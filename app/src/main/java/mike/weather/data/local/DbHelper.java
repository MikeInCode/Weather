package mike.weather.data.local;

import io.reactivex.Observable;
import mike.weather.data.model.City;

public interface DbHelper {
    void insertCity(City city);
    Observable<City> readAllCities();
    void deleteCity(City city);
}
