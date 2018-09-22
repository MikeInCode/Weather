package mike.weather.injection.component;

import dagger.Subcomponent;
import mike.weather.injection.module.MainActivityModule;
import mike.weather.injection.scope.ActivityScope;
import mike.weather.ui.main.MainActivity;

@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
