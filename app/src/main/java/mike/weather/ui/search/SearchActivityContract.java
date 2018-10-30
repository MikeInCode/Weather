package mike.weather.ui.search;

import java.util.List;

import io.reactivex.Observable;
import mike.weather.data.model.City;
import mike.weather.data.model.SearchData;
import mike.weather.ui.base.IBasePresenter;
import mike.weather.ui.base.IBaseView;

public interface SearchActivityContract {
    interface View extends IBaseView {
        void showSuggestedCitiesList(List<SearchData> suggestedList);
        void hideSuggestedCitiesList();
        void showCityNotFoundMessage();
        void hideCityNotFoundMessage();
    }

    interface Presenter extends IBasePresenter<View> {
        void setTextChangeObservable(Observable<CharSequence> observable);
        void cityClicked(City cityToAdd);
    }
}
