package mike.weather.data.local;

import java.util.List;

import mike.weather.data.model.City;

public interface DbHelper {
    void insertCity(City city);
    List<City> readAllCities();
}
