package mike.weather.ui.main;

import javax.inject.Inject;

import mike.weather.data.IDataManager;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mView;
    private IDataManager dataManager;

    @Inject
    public MainActivityPresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attach(MainActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void refreshWeatherList() {

    }
}
