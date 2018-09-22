package mike.weather.ui.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mike.weather.R;
import mike.weather.data.model.WeatherInfo;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private MainActivityContract.View mView;

    @Inject
    public MainActivityPresenter() {

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
        List<WeatherInfo> citiesList = new ArrayList<>();
        citiesList.add(new WeatherInfo("Odessa", "Ukraine", "23°", R.drawable.ic_sunny));
        citiesList.add(new WeatherInfo("Kiev", "Ukraine", "19°", R.drawable.ic_sunny));
        mView.showCitiesList(citiesList);
    }
}
