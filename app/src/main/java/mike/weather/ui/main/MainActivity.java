package mike.weather.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.injection.module.MainActivityModule;
import mike.weather.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View,
        SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.cites_recycler_view)
    RecyclerView citiesRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
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

        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        citiesRecyclerView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.end_color);

        presenter.attach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateCitiesList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void onRefresh() {
        presenter.updateCitiesList();
    }

    @OnClick(R.id.add_city_btn)
    public void addCityBtn() {
        presenter.addCityBtnClicked();
    }

    @Override
    public void showCitiesList(List<City> citiesList) {
        adapter.setCitiesList(citiesList);
    }

    @Override
    public void showServerErrorToast() {
        Toast.makeText(this, "Server error!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInternetErrorToast() {
        Toast.makeText(this, "Internet connection error!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideRefreshingStatus() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void goToSearch() {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
