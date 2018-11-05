package mike.weather.ui.detailed;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;

import javax.inject.Inject;

import butterknife.BindView;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.injection.module.DetailedActivityModule;
import mike.weather.ui.base.BaseActivity;
import mike.weather.ui.base.OnItemClickListener;

public class DetailedActivity extends BaseActivity implements DetailedActivityContract.View {

    @BindView(R.id.forecast_recycler_view)
    RecyclerView forecastRecyclerView;
    @BindView(R.id.forecast_spinner)
    AppCompatSpinner forecastSpinner;
    @BindView(R.id.refresh_btn)
    ImageButton refreshBtn;
    @BindView(R.id.aeris_weather)
    TextView aerisWeather;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
    @BindView(R.id.city_name)
    TextView cityName;
    @BindView(R.id.weather_icon)
    ImageView icon;
    @BindView(R.id.current_temp)
    TextView currentTemp;
    @BindView(R.id.feels_like_value)
    TextView feelsLikeTemp;
    @BindView(R.id.sunrise_value)
    TextView sunrise;
    @BindView(R.id.sunset_value)
    TextView sunset;
    @BindView(R.id.humidity_value)
    TextView humidity;
    @BindView(R.id.wind_value)
    TextView wind;
    @BindView(R.id.clouds_value)
    TextView cloudsCoverage;
    @BindView(R.id.pressure_value)
    TextView pressure;
    @Inject
    DetailedActivityContract.Presenter presenter;
    @Inject
    ForecastOneDayAdapter forecastOneDayAdapter;
    @Inject
    ForecastOneWeekAdapter forecastOneWeekAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        App.getAppComponent().plus(new DetailedActivityModule()).inject(this);

        presenter.attach(this);
        presenter.initCity(new Gson().fromJson(getIntent().getStringExtra("city"), City.class));
        presenter.setSpinnerObservable(RxAdapterView.itemSelections(forecastSpinner));
        presenter.setRefreshObservable(RxView.clicks(refreshBtn));

        backBtn.setOnClickListener(l -> presenter.backBtnClicked());
        aerisWeather.setOnClickListener(l -> presenter.aerisWeatherClicked());

        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showCityData(City city) {
        showCityName(city);
        showCityConditions(city);
        showCityForecast(city);
    }

    private void showCityName(City city) {
        cityName.setText(city.getCityName());
    }

    private void showCityConditions(City city) {
        icon.setImageResource(city.getCurrentConditions().getIcon());
        currentTemp.setText(city.getCurrentConditions().getTempCelsius());
        feelsLikeTemp.setText(city.getCurrentConditions().getFeelsLikeTempCelsius());
        sunrise.setText(city.getCurrentConditions().getSunrise());
        sunset.setText(city.getCurrentConditions().getSunset());
        humidity.setText(city.getCurrentConditions().getHumidity());
        wind.setText(city.getCurrentConditions().getWindSpeedKPH());
        cloudsCoverage.setText(city.getCurrentConditions().getCloudsCoverage());
        pressure.setText(city.getCurrentConditions().getPressureMillibars());
    }

    private void showCityForecast(City city) {
        switch (forecastSpinner.getSelectedItemPosition()) {
            case 0:
                forecastRecyclerView.setAdapter(forecastOneDayAdapter);
                forecastOneDayAdapter.setList(city.getForecastList());
                break;
            case 1:
                forecastRecyclerView.setAdapter(forecastOneWeekAdapter);
                forecastOneWeekAdapter.setList(city.getForecastList());
                break;
        }
    }
}
