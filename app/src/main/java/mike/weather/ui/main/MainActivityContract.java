package mike.weather.ui.main;

import java.util.List;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.ui.base.BasePresenter;
import mike.weather.ui.base.BaseView;

public interface MainActivityContract {
    interface View extends BaseView {
        void showCitiesList(List<City> citiesList);
        void showDate(String date);
        void hideRefreshingStatus();
        void deleteCityFromList(int position);
        void goToSearch();
        void goToApiWebsite();
    }

    interface Presenter extends BasePresenter<View> {
        void onPause();
        void setCitiesList();
        void setRefreshObservable(Observable<Object> observable);
        void setAddBtnObservable(Observable<Object> observable);
        void setAerisWeatherObservable(Observable<Object> observable);
        void itemSwipedToDelete(int position, City cityToDelete);
    }
}
