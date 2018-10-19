package mike.weather.ui.search;

import java.util.List;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.ui.base.BasePresenter;
import mike.weather.ui.base.BaseView;

public interface SearchActivityContract {
    interface View extends BaseView {
        void showSuggestedCitiesList(List<City> suggestedList);
        void hideSuggestedCitiesList();
        void showCityNotFoundMessage();
        void hideCityNotFoundMessage();
        void goBack();
    }

    interface Presenter extends BasePresenter<View> {
        void setTextChangeObservable(Observable<CharSequence> observable);
        void setBackBtnObservable(Observable<Object> observable);
        void cityClicked(City cityToAdd);
    }
}
