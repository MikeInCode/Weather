package mike.weather.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.cites_recycler_view)
    RecyclerView citiesRecyclerView;
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

        presenter.attach(this);
        presenter.refreshWeatherList();
    }

    @Override
    public void showCitiesList(List<City> citiesList) {
        adapter.setmCitiesList(citiesList);
    }

    @Override
    public void goToSearch() {
        startActivity(new Intent(this, SearchActivity.class));
    }

    @OnClick(R.id.add_city_btn)
    public void addCityBtn() {
        presenter.addCityBtnClicked();
    }

}
