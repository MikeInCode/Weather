package mike.weather.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import mike.weather.injection.module.AppModule;
import mike.weather.injection.module.DataModule;
import mike.weather.injection.module.MainActivityModule;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    MainActivityComponent plus(MainActivityModule mainActivityModule);
}
