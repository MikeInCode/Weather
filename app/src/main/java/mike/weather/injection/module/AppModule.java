package mike.weather.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.AppDataManager;
import mike.weather.data.DataManager;
import mike.weather.data.local.AppPreferencesHelper;
import mike.weather.data.local.PreferencesHelper;

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
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
