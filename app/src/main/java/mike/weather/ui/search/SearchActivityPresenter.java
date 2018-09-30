package mike.weather.ui.search;

import java.util.List;

import javax.inject.Inject;

import mike.weather.data.AppDataManager;
import mike.weather.data.DataManager;
import mike.weather.data.model.City;

public class SearchActivityPresenter implements SearchActivityContract.Presenter {

    private SearchActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public SearchActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
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
            dataManager.getSuggestedCitiesList(searchingQuery, new AppDataManager.Callback() {
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
                public void onError() {
                    // TODO View, you need to inform that something goes wrong
                }
            });
        } else {
            view.hideSuggestedCitiesList();
            view.hideCityNotFoundMessage();
        }
    }

    @Override
    public void cityClicked(String cityKey) {
        dataManager.addCityToDb(cityKey);
    }

    @Override
    public void backBtnClicked() {
        view.goBack();
    }
}
