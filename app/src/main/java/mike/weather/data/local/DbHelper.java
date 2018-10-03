package mike.weather.data.local;

import mike.weather.data.remote.WeatherApi;

public interface DbHelper {
    void insertCity(WeatherApi.SearchCity searchCity);
}
