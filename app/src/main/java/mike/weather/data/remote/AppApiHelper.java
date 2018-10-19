package mike.weather.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import mike.weather.data.model.ConditionsResponse;
import mike.weather.data.model.SearchResponse;


@Singleton
public class AppApiHelper implements ApiHelper {

    private WeatherApi weatherApi;

    @Inject
    public AppApiHelper(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public Single<SearchResponse> makeCitySearchRequest(String query) {
        String formattedQuery = "name:^" + query;
        return weatherApi.getSearchingResult(formattedQuery, WeatherApi.id, WeatherApi.secret);
    }

    @Override
    public Single<ConditionsResponse> makeCurrentConditionsRequest(String cityQuery) {
        return weatherApi.getCurrentCityConditions(cityQuery, WeatherApi.id, WeatherApi.secret);
    }
}
