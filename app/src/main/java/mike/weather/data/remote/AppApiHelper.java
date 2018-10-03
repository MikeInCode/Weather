package mike.weather.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.ui.search.SearchActivityPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppApiHelper implements ApiHelper {

    private WeatherApi weatherApi;

    @Inject
    public AppApiHelper(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    @Override
    public void makeCitySearchQuery(String searchingQuery, SearchActivityPresenter.SearchCallback callback) {
        weatherApi.getSearchingResult(WeatherApi.apiKey, searchingQuery).enqueue(new Callback<List<WeatherApi.SearchCity>>() {
            @Override
            public void onResponse(Call<List<WeatherApi.SearchCity>> call, Response<List<WeatherApi.SearchCity>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<WeatherApi.SearchCity>> call, Throwable t) {

            }
        });
    }
}
