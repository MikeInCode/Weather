package mike.weather.ui.search;

import java.util.List;

import javax.inject.Inject;

import mike.weather.data.DataManager;
import mike.weather.data.model.SearchCity;
import mike.weather.ui.base.BaseError;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public SearchActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public interface Callback extends BaseError {
        void onSuccess(List<SearchCity> suggestedList);
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
    public void updateSuggestedCitiesList(String searchingQuery) {
        if (searchingQuery.length() > 0) {
            dataManager.getSuggestedCitiesList(searchingQuery, new Callback() {
                @Override
                public void onSuccess(List<SearchCity> suggestedList) {
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
        } else {
            view.hideSuggestedCitiesList();
            view.hideCityNotFoundMessage();
        }
    }

    @Override
    public void cityClicked(SearchCity searchCity) {
        dataManager.addCityToDb(searchCity);
        view.goBack();
    }

    @Override
    public void backBtnClicked() {
        view.goBack();
    }
}
