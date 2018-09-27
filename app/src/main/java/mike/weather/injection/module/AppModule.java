package mike.weather.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.AppDataManager;
import mike.weather.data.DataManager;
import mike.weather.data.local.AppDbHelper;
import mike.weather.data.local.DbHelper;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

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
