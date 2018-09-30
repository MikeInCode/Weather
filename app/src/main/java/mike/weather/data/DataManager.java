package mike.weather.data;

public interface DataManager {
    void getSuggestedCitiesList(String searchingQuery, AppDataManager.Callback callback);
    void addCityToDb(String cityKey);
}
