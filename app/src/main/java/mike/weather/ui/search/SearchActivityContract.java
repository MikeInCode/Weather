package mike.weather.ui.search;

import java.util.List;

import mike.weather.data.model.City;
import mike.weather.ui.base.BasePresenter;

public interface SearchActivityContract {
    interface View {
        void showSuggestedCitiesList(List<City> suggestedList);
        void hideSuggestedCitiesList();
        void showCityNotFoundMessage();
        void hideCityNotFoundMessage();
        void goBack();
    }

    interface Presenter extends BasePresenter<View> {
        void updateSuggestedCitiesList(String searchingQuery);
        void cityClicked(String cityKey);
        void backBtnClicked();
    }
}
