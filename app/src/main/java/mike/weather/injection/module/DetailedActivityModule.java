package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.ui.detailed.DetailedActivityContract;
import mike.weather.ui.detailed.DetailedActivityPresenter;

@Module
public class DetailedActivityModule {

    @Provides
    DetailedActivityContract.Presenter providePresenter(DetailedActivityPresenter presenter) {
        return presenter;
    }
}
