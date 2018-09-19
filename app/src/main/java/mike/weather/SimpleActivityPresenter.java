package mike.weather;

import java.util.ArrayList;
import java.util.List;

public class SimpleActivityPresenter implements SimpleActivityContract.Presenter {
    private SimpleActivityContract.View mView;

    public SimpleActivityPresenter() {

    }

    @Override
    public void attach(SimpleActivityContract.View view) {
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
