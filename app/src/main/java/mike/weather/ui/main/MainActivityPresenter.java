package mike.weather.ui.main;

import javax.inject.Inject;

import mike.weather.data.DataManager;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View view;
    private DataManager dataManager;

    @Inject
    public MainActivityPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
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
    public void refreshWeatherList() {

    }

    @Override
    public void addCityBtnClicked() {
        view.goToSearch();
    }
}
