package mike.weather.ui.detailed;

import android.widget.Spinner;

import java.util.List;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.data.model.Conditions;
import mike.weather.ui.base.IBasePresenter;
import mike.weather.ui.base.IBaseView;

public interface DetailedActivityContract {
    interface View extends IBaseView {
        void showCityInfo(City city);
        void showForecast(List<Conditions> forecastList);
    }

    interface Presenter extends IBasePresenter<View> {
        void initCity(City mainCity);
        void setCityData();
        void setForecastData();
        void setRefreshObservable(Observable<Object> observable);
    }
}
