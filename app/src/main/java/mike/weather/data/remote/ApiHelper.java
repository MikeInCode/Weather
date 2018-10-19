package mike.weather.data.remote;

import io.reactivex.Single;
import mike.weather.data.model.ConditionsResponse;
import mike.weather.data.model.SearchResponse;

public interface ApiHelper {
    Single<SearchResponse> makeCitySearchRequest(String query);
    Single<ConditionsResponse> makeCurrentConditionsRequest(String cityQuery);
}
