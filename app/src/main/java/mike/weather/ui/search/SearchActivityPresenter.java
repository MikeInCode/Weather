package mike.weather.ui.search;

import java.util.List;

import javax.inject.Inject;

import mike.weather.data.DataManager;
import mike.weather.data.remote.WeatherApi;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public SearchActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public interface SearchCallback {
        void onSuccess(List<WeatherApi.SearchCity> suggestedList);
        void onError();
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
            dataManager.getSuggestedCitiesList(searchingQuery, new SearchCallback() {
                @Override
                public void onSuccess(List<WeatherApi.SearchCity> suggestedList) {
                    if (!suggestedList.isEmpty()) {
                        view.showSuggestedCitiesList(suggestedList);
                        view.hideCityNotFoundMessage();
                    } else {
                        view.hideSuggestedCitiesList();
                        view.showCityNotFoundMessage();
                    }
                }

                @Override
                public void onError() {
                    //TODO onError logic
                }
            });
        } else {
            view.hideSuggestedCitiesList();
            view.hideCityNotFoundMessage();
        }
    }

    @Override
    public void cityClicked(WeatherApi.SearchCity searchCity) {
        dataManager.addCityToDb(searchCity);
        view.goBack();
    }

    @Override
    public void backBtnClicked() {
        view.goBack();
    }
}
