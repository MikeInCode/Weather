package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.local.AppDbHelper;
import mike.weather.data.local.DbHelper;

@Module
public class DatabaseModule {

    @Provides
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    String provideDbName() {
        return "main_db";
    }

    @Provides
    int provideDbVersion() {
        return 1;
    }
}
