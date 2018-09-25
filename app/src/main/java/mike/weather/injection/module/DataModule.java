package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.DataManager;
import mike.weather.data.IDataManager;
import mike.weather.data.local.DbHelper;
import mike.weather.data.local.IDbHelper;

@Module
public class DataModule {

    @Provides
    IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }

    @Provides
    IDbHelper provideDbHelper(DbHelper dbHelper) {
        return dbHelper;
    }
}
