package mike.weather.ui.main;

import java.util.List;

import mike.weather.data.model.WeatherInfo;
import mike.weather.ui.base.BasePresenter;

public interface MainActivityContract {
    interface View {
        void showCitiesList(List<WeatherInfo> citiesList);
    }

    interface Presenter extends BasePresenter<View> {
        void refreshWeatherList();
    }
}
