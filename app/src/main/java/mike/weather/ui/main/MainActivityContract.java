package mike.weather.ui.main;

import java.util.List;

import mike.weather.data.model.City;
import mike.weather.ui.base.BasePresenter;

public interface MainActivityContract {
    interface View {
        void showCitiesList(List<City> citiesList);
        void showServerErrorToast();
        void showInternetErrorToast();
        void hideRefreshingStatus();
        void showLastUpdateDate(String date);
        void showUnitsSwitcherState(boolean state);
        void goToSearch();
    }

    interface Presenter extends BasePresenter<View> {
        void updateCitiesList();
        void addCityBtnClicked();
        void setUnitsSwitcherState();
        void unitsSwitcherClicked();
    }
}
