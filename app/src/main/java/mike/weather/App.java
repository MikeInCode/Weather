package mike.weather;

import android.app.Application;
import android.content.ContextWrapper;
import android.content.res.Resources;

import mike.weather.injection.component.AppComponent;
import mike.weather.injection.component.DaggerAppComponent;
import mike.weather.injection.module.AppModule;

public class App extends Application {

    private static AppComponent appComponent;
    private static Resources res;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Resources getRes() {
        return res;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        res = getResources();
    }
}
