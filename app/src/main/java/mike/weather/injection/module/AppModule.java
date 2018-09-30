package mike.weather.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.AppDataManager;
import mike.weather.data.DataManager;

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
}
