package mike.weather.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mike.weather.data.remote.ApiHelper;
import mike.weather.data.remote.IApiHelper;
import mike.weather.data.remote.WeatherApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    IApiHelper provideApiHelper(ApiHelper apiHelper) {
        return apiHelper;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.aerisapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
