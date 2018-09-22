package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.ui.main.MainActivityContract;
import mike.weather.ui.main.MainActivityPresenter;

@Module
public class MainActivityModule {

    @Provides
    MainActivityContract.Presenter providePresenter(MainActivityPresenter presenter) {
        return presenter;
    }
}
