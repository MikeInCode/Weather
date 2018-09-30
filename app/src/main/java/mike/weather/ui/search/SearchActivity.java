package mike.weather.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.injection.module.SearchActivityModule;

public class SearchActivity extends AppCompatActivity implements SearchActivityContract.View,
        SearchView.OnQueryTextListener, SearchCitiesAdapter.onItemClickListener {

    @BindView(R.id.cites_recycler_view)
    RecyclerView citiesRecyclerView;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.city_not_found)
    TextView cityNotFoundMessage;
    @Inject
    SearchActivityContract.Presenter presenter;
    @Inject
    SearchCitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        App.getAppComponent().plus(new SearchActivityModule()).inject(this);

        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        presenter.attach(this);

        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        presenter.updateSuggestedCitiesList(s);
        return false;
    }

    @Override
    public void showSuggestedCitiesList(List<City> suggestedList) {
        adapter.setSuggestedCitiesList(suggestedList);
    }

    @Override
    public void hideSuggestedCitiesList() {
        adapter.clearSuggestedCitiesList();
    }

    @Override
    public void showCityNotFoundMessage() {
        cityNotFoundMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCityNotFoundMessage() {
        cityNotFoundMessage.setVisibility(View.GONE);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.back_btn)
    public void backBtn() {
        presenter.backBtnClicked();
    }

    @Override
    public void onItemClick(String cityKey) {
        Toast.makeText(SearchActivity.this, cityKey, Toast.LENGTH_LONG).show();
        presenter.cityClicked(cityKey);
    }
}
