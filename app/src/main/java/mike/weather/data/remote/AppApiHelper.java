package mike.weather.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.model.ApiResponse;
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
    public void makeCitySearchQuery(String searchingPhrase, SearchActivityPresenter.Callback callback) {
        weatherApi.getSearchingResult(searchingPhrase, WeatherApi.apiKey).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getCitiesList());
                } else {
                    callback.onServerError();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                callback.onInternetError();
            }
        });
    }

    @Override
    public void makeCurrentConditionsQuery(String citiesIds, String units, MainActivityPresenter.Callback callback) {
        weatherApi.getCurrentCityConditions(citiesIds, units, WeatherApi.apiKey).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getCitiesList());
                } else {
                    callback.onServerError(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                callback.onInternetError(null);
            }
        });
    }
}
