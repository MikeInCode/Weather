package mike.weather.data.remote;

import java.util.List;

import io.reactivex.Single;
import mike.weather.data.model.ApiResponse;
import mike.weather.data.model.ConditionsData;
import mike.weather.data.model.ForecastData;
import mike.weather.data.model.SearchData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    String id = "SM0bBPNWhfb0H4YceTF9V";
    String secret = "txCS1xu9rttUlIkpimfDpykDZzCBtJmx2W8MH3Sk";

    @GET("places/search?limit=5")
    Single<ApiResponse<List<SearchData>>> getSearchingResult(@Query("query") String formattedQuery,
                                                             @Query("client_id") String id,
                                                             @Query("client_secret") String secret);

    @GET("observations/{city_query}")
    Single<ApiResponse<ConditionsData>> getCurrentCityConditions(@Path("city_query") String cityQuery,
                                                                 @Query("client_id") String id,
                                                                 @Query("client_secret") String secret);

    //https://api.aerisapi.com/forecasts/odessa,ua?filter=1hr&limit=24&client_id=SM0bBPNWhfb0H4YceTF9V&client_secret=txCS1xu9rttUlIkpimfDpykDZzCBtJmx2W8MH3Sk

    @GET("forecasts/{city_query}")
    Single<ApiResponse<List<ForecastData>>> getCityForecast(@Path("city_query") String cityQuery,
                                                            @Query("filter") String timeInterval,
                                                            @Query("limit") String responseSize,
                                                            @Query("client_id") String id,
                                                            @Query("client_secret") String secret);
}
