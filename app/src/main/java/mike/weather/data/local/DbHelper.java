package mike.weather.data.local;

import java.util.List;

import mike.weather.data.model.MainCity;
import mike.weather.data.model.SearchCity;

public interface DbHelper {
    void insertCity(SearchCity searchCity);
    List<MainCity> readAllCities();
}
