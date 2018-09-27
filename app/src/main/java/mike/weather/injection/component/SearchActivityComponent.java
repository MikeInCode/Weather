package mike.weather.injection.component;

import dagger.Subcomponent;
import mike.weather.injection.module.SearchActivityModule;
import mike.weather.injection.scope.ActivityScope;
import mike.weather.ui.search.SearchActivity;

@ActivityScope
@Subcomponent(modules = SearchActivityModule.class)
public interface SearchActivityComponent {
    void inject(SearchActivity searchActivity);
}
