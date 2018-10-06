package mike.weather.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.remote.ApiHelper;
import mike.weather.data.remote.AppApiHelper;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    ApiHelper provideApiHelper(AppApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
