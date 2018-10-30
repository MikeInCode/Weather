package mike.weather.ui.main;

import java.util.List;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.ui.base.IBasePresenter;
import mike.weather.ui.base.IBaseView;

public interface MainActivityContract {
    interface View extends IBaseView {
        void showCitiesList(List<City> citiesList);
        void deleteCityFromList(int position);
        void hideRefreshingStatus();
        void goToDetailedInfo(City city);
        void goToSearch();
    }

    interface Presenter extends IBasePresenter<View> {
        void pause();
        void setCitiesList();
        void setRefreshObservable(Observable<Object> observable);
        void itemSwipedToDelete(int position, City cityToDelete);
        void cityClicked(City city);
        void addCityBtnClicked();
    }
}
