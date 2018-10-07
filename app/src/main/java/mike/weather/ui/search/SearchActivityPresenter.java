package mike.weather.ui.search;

import java.util.List;

import javax.inject.Inject;

import mike.weather.data.DataManager;
import mike.weather.data.model.City;
import mike.weather.ui.base.BaseError;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public SearchActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public interface Callback extends BaseError {
        void onSuccess(List<City> suggestedList);
    }

    @Override
    public void attach(SearchActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void updateSuggestedCitiesList(String searchingPhrase) {
        if (searchingPhrase.length() > 2) {
            dataManager.getSuggestedCitiesList(searchingPhrase, new Callback() {
                @Override
                public void onSuccess(List<City> suggestedList) {
                    if (!suggestedList.isEmpty()) {
                        view.showSuggestedCitiesList(suggestedList);
                        view.hideCityNotFoundMessage();
                    } else {
                        view.hideSuggestedCitiesList();
                        view.showCityNotFoundMessage();
                    }
                }

                @Override
                public void onServerError() {
                    view.showServerErrorToast();
                }

                @Override
                public void onInternetError() {
                    view.showInternetErrorToast();
                }
            });
        } else if (searchingPhrase.length() == 0) {
            view.hideSuggestedCitiesList();
            view.hideCityNotFoundMessage();
        } else {
            view.hideSuggestedCitiesList();
            view.showCityNotFoundMessage();
        }
    }

    @Override
    public void cityClicked(City cityToAdd) {
        if (!dataManager.addCityToDb(cityToAdd)) {
            view.showCitiesLimitToast();
        }
        view.goBack();
    }

    @Override
    public void backBtnClicked() {
        view.goBack();
    }
}
