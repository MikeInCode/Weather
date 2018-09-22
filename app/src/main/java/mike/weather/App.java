package mike.weather;

import android.app.Application;

import mike.weather.injection.component.AppComponent;
import mike.weather.injection.component.DaggerAppComponent;
import mike.weather.injection.module.AppModule;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
