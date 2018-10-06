package mike.weather.ui.main;

import java.util.List;

import mike.weather.data.model.City;
import mike.weather.ui.base.BasePresenter;

public interface MainActivityContract {
    interface View {
        void showCitiesList(List<City> citiesList);
        void showServerErrorToast();
        void showInternetErrorToast();
        void goToSearch();
    }

    interface Presenter extends BasePresenter<View> {
        void updateCitiesList();
        void addCityBtnClicked();
    }
}
