package mike.weather.injection.module;

import dagger.Module;
import dagger.Provides;
import mike.weather.ui.search.SearchActivityContract;
import mike.weather.ui.search.SearchActivityPresenter;

@Module
public class SearchActivityModule {

    @Provides
    SearchActivityContract.Presenter providePresenter(SearchActivityPresenter presenter) {
        return presenter;
    }
}
