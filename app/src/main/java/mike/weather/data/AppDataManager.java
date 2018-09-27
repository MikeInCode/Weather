package mike.weather.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import mike.weather.data.local.DbHelper;

@Singleton
public class AppDataManager implements DataManager {

    private DbHelper dbHelper;

    @Inject
    public AppDataManager(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }
}
