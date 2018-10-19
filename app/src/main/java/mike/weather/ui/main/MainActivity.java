package mike.weather.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.injection.module.MainActivityModule;
import mike.weather.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.cites_recycler_view)
    RecyclerView citiesRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.add_city_btn)
    ImageButton addCityBtn;
    @BindView(R.id.refresh_btn)
    ImageButton refreshBtn;
    @BindView(R.id.aerisweather)
    TextView aerisWeather;
    @BindView(R.id.last_update_date)
    TextView lastUpdateDate;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @Inject
    MainActivityContract.Presenter presenter;
    @Inject
    MainCitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().plus(new MainActivityModule()).inject(this);

        presenter.attach(this);
        presenter.setRefreshObservable(RxSwipeRefreshLayout.refreshes(swipeRefreshLayout));
        presenter.setRefreshObservable(RxView.clicks(refreshBtn));
        presenter.setAddBtnObservable(RxView.clicks(addCityBtn));
        presenter.setAerisWeatherObservable(RxView.clicks(aerisWeather));

        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        citiesRecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_primary);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setCitiesList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showCitiesList(List<City> citiesList) {
        adapter.setCitiesList(citiesList);
    }

    @Override
    public void showDate(String date) {
        lastUpdateDate.setText(date);
    }

    @Override
    public void hideRefreshingStatus() {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToSearch() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @Override
    public void goToApiWebsite() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aerisweather.com")));
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}
