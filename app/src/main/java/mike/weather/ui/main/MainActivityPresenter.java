package mike.weather.ui.main;

import java.util.List;

import javax.inject.Inject;

import mike.weather.data.DataManager;
import mike.weather.data.model.City;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public MainActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public interface Callback {
        void onSuccess(List<City> updatedCitiesList);
        void onServerError(List<City> oldCitiesList);
        void onInternetError(List<City> oldCitiesList);
    }

    @Override
    public void attach(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void updateCitiesList() {
        dataManager.getMainCitiesList(new Callback() {
            @Override
            public void onSuccess(List<City> updatedCitiesList) {
                view.showCitiesList(updatedCitiesList);
            }

            @Override
            public void onServerError(List<City> oldCitiesList) {
                view.showCitiesList(oldCitiesList);
                view.showServerErrorToast();
            }

            @Override
            public void onInternetError(List<City> oldCitiesList) {
                view.showCitiesList(oldCitiesList);
                view.showInternetErrorToast();
            }
        });
    }

    @Override
    public void addCityBtnClicked() {
        view.goToSearch();
    }
}
