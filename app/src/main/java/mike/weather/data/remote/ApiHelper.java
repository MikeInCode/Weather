package mike.weather.data.remote;

public interface ApiHelper {
    void makeCitySearchQuery(String searchingQuery, AppApiHelper.Callback callback);
}
