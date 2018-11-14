package mike.weather.ui.detailed;

import com.jakewharton.rxbinding2.InitialValueObservable;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.ui.base.IBasePresenter;
import mike.weather.ui.base.IBaseView;

public interface DetailedActivityContract {
    interface View extends IBaseView {
        void showCityData(City city);
    }

    interface Presenter extends IBasePresenter<View> {
        void initCity(City mainCity);
        void setSpinnerObservable(InitialValueObservable<Integer> observable);
        void setRefreshObservable(Observable<Object> observable);
    }
}
