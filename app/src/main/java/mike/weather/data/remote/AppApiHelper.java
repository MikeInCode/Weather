package mike.weather.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.model.CurrentConditions;
import mike.weather.data.model.SearchCity;
import mike.weather.ui.main.MainActivityPresenter;
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
    public void makeCitySearchQuery(String searchingQuery, SearchActivityPresenter.Callback callback) {
        weatherApi.getSearchingResult(WeatherApi.apiKey, searchingQuery).enqueue(new Callback<List<SearchCity>>() {
            @Override
            public void onResponse(Call<List<SearchCity>> call, Response<List<SearchCity>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onServerError();
                }
            }

            @Override
            public void onFailure(Call<List<SearchCity>> call, Throwable t) {
                callback.onInternetError();
            }
        });
    }

    @Override
    public void makeCurrentConditionsQuery(String cityKey, MainActivityPresenter.Callback callback) {
        weatherApi.getCurrentCityConditions(cityKey, WeatherApi.apiKey).enqueue(new Callback<List<CurrentConditions>>() {
            @Override
            public void onResponse(Call<List<CurrentConditions>> call, Response<List<CurrentConditions>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body(), null);
                } else {
                    callback.onServerError(null);
                }
            }

            @Override
            public void onFailure(Call<List<CurrentConditions>> call, Throwable t) {
                callback.onInternetError(null);
            }
        });
    }
}
