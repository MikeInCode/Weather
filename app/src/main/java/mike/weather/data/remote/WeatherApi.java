package mike.weather.data.remote;

import java.util.List;

import mike.weather.data.model.CurrentConditions;
import mike.weather.data.model.SearchCity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    String apiKey = "";

    @GET("locations/v1/cities/autocomplete")
    Call<List<SearchCity>> getSearchingResult(@Query("apikey") String apiKey, @Query("q") String searchingQuery);

    @GET("currentconditions/v1/{city_key}")
    Call<List<CurrentConditions>> getCurrentCityConditions(@Path("city_key") String cityKey,
                                                           @Query("apikey") String apiKey);

}
