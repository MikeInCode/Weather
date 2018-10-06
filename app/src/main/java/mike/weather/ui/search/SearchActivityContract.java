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
        void showServerErrorToast();
        void showInternetErrorToast();
        void goBack();
    }

    interface Presenter extends BasePresenter<View> {
        void updateSuggestedCitiesList(String searchingPhrase);
        void cityClicked(City cityToAdd);
        void backBtnClicked();
    }
}
