package mike.weather.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import mike.weather.data.model.ApiResponse;
import mike.weather.data.model.ConditionsData;
import mike.weather.data.model.ForecastData;
import mike.weather.data.model.SearchData;


@Singleton
public class AppApiHelper implements ApiHelper {

    private WeatherApi weatherApi;

    @Inject
    public AppApiHelper(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<ApiResponse<List<SearchData>>> makeCitySearchRequest(String query) {
        String formattedQuery = "name:^" + query;
        return weatherApi.getSearchingResult(formattedQuery, WeatherApi.id, WeatherApi.secret);
    }

    @Override
    public Single<ApiResponse<ConditionsData>> makeCurrentConditionsRequest(String cityQuery) {
        return weatherApi.getCurrentCityConditions(cityQuery, WeatherApi.id, WeatherApi.secret);
    }

    @Override
    public Single<ApiResponse<List<ForecastData>>> makeForecastRequest(String cityQuery,
                                                                       String timeInterval,
                                                                       String responseSize) {
        return weatherApi.getCityForecast(cityQuery, timeInterval, responseSize, WeatherApi.id, WeatherApi.secret);
    }
}
