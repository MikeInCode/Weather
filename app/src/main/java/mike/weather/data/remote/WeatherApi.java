package mike.weather.data.remote;

import java.util.List;

import mike.weather.data.model.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    String apiKey = "";

    @GET("locations/v1/cities/autocomplete")
    Call<List<City>> getSearchingResult(@Query("apikey") String apiKey, @Query("q") String searchingQuery);
}
