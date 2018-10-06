package mike.weather.data.remote;

import mike.weather.data.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    String apiKey = "551bdafad07fe3a34599fb62473a81a7";

    @GET("find")
    Call<ApiResponse> getSearchingResult(@Query("q") String searchingPhrase, @Query("appid") String apiKey);

    @GET("group")
    Call<ApiResponse> getCurrentCityConditions(@Query("id") String citiesIds,
                                               @Query("units") String unitsValue,
                                               @Query("appid") String apiKey);
}
