package mike.weather.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.local.DbHelper;
import mike.weather.data.model.City;
import mike.weather.data.remote.ApiHelper;
import mike.weather.data.remote.AppApiHelper;

@Singleton
public class AppDataManager implements DataManager {

    private ApiHelper apiHelper;
    private DbHelper dbHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper, DbHelper dbHelper) {
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
    }

    public interface Callback {
        void onSuccess(List<City> suggestedList);
        void onError();
    }

    @Override
    public void getSuggestedCitiesList(String searchingQuery, Callback callback) {
        apiHelper.makeCitySearchQuery(searchingQuery, new AppApiHelper.Callback() {
            @Override
            public void onSuccess(List<City> suggestedList) {
                callback.onSuccess(suggestedList);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void addCityToDb(String cityKey) {
        // TODO DbHelper make adding
    }
}
