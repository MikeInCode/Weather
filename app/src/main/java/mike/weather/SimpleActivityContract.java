package mike.weather;

import java.util.List;

public interface SimpleActivityContract {
    interface View {
        void showCitiesList(List<WeatherInfo> citiesList);
    }

    interface Presenter extends BasePresenter<View> {
        void refreshWeatherList();
    }
}
