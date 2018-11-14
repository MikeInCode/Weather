package mike.weather.data.remote;

import java.util.List;

import io.reactivex.Single;
import mike.weather.data.model.ApiResponse;
import mike.weather.data.model.ConditionsData;
import mike.weather.data.model.ForecastData;
import mike.weather.data.model.SearchData;

public interface IApiHelper {
    Single<ApiResponse<List<SearchData>>> makeCitySearchRequest(String query);
    Single<ApiResponse<ConditionsData>> makeCurrentConditionsRequest(String cityQuery);
    Single<ApiResponse<List<ForecastData>>> makeForecastRequest(String cityQuery,
                                                                String timeInterval,
                                                                String responseSize);
}
