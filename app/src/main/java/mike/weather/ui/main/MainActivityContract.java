package mike.weather.ui.main;

import java.util.List;

import mike.weather.data.model.CityInfo;
import mike.weather.ui.base.BasePresenter;

public interface MainActivityContract {
    interface View {
        void showCitiesList(List<CityInfo> citiesList);
    }

    interface Presenter extends BasePresenter<View> {
        void refreshWeatherList();
    }
}
