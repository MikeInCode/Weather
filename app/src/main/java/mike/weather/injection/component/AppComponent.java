package mike.weather.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import mike.weather.injection.module.AppModule;
import mike.weather.injection.module.DatabaseModule;
import mike.weather.injection.module.MainActivityModule;
import mike.weather.injection.module.NetworkModule;
import mike.weather.injection.module.SearchActivityModule;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {
    MainActivityComponent plus(MainActivityModule mainActivityModule);
    SearchActivityComponent plus(SearchActivityModule searchActivityModule);
}
