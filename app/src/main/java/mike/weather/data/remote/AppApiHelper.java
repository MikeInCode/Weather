package mike.weather.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.model.City;
import retrofit2.Call;
import retrofit2.Response;

@Singleton
public class AppApiHelper implements ApiHelper {

    private WeatherApi weatherApi;

    @Inject
    public AppApiHelper(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public interface Callback {
        void onSuccess(List<City> suggestedList);
        void onError();
    }

    @Override
    public void makeCitySearchQuery(String searchingQuery, Callback callback) {
        weatherApi.getSearchingResult(WeatherApi.apiKey, searchingQuery).enqueue(new retrofit2.Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                callback.onError();
            }
        });
    }
}
