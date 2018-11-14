package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.local.DbHelper;
import mike.weather.data.local.IDbHelper;

@Module
public class DatabaseModule {

    @Provides
    IDbHelper provideDbHelper(DbHelper dbHelper) {
        return dbHelper;
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
