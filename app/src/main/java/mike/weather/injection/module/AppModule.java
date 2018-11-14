package mike.weather.injection.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import mike.weather.data.DataManager;
import mike.weather.data.IDataManager;

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
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    IDataManager provideDataManager(DataManager dataManager) {
        return dataManager;
    }
}
