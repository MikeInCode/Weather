package mike.weather.injection.component;

import dagger.Subcomponent;
import mike.weather.injection.module.DetailedActivityModule;
import mike.weather.injection.scope.ActivityScope;
import mike.weather.ui.detailed.DetailedActivity;

@ActivityScope
@Subcomponent(modules = DetailedActivityModule.class)
public interface DetailedActivityComponent {
    void inject(DetailedActivity detailedActivity);
}
