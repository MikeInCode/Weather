package mike.weather.data.remote;

import io.reactivex.Single;
import mike.weather.data.model.ConditionsResponse;
import mike.weather.data.model.SearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {
    String id = "SM0bBPNWhfb0H4YceTF9V";
    String secret = "txCS1xu9rttUlIkpimfDpykDZzCBtJmx2W8MH3Sk";

    @GET("places/search?limit=5")
    Single<SearchResponse> getSearchingResult(@Query("query") String formattedQuery,
                                              @Query("client_id") String id,
                                              @Query("client_secret") String secret);

    @GET("observations/{city_query}&filter=allstations")
    Single<ConditionsResponse> getCurrentCityConditions(@Path("city_query") String cityQuery,
                                                        @Query("client_id") String id,
                                                        @Query("client_secret") String secret);
}
