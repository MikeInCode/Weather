package mike.weather.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.injection.module.SearchActivityModule;
import mike.weather.ui.base.OnItemClickListener;

public class SearchActivity extends AppCompatActivity implements SearchActivityContract.View,
        OnItemClickListener {

    @BindView(R.id.cites_recycler_view)
    RecyclerView citiesRecyclerView;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.city_not_found)
    TextView cityNotFoundMessage;
    @BindView(R.id.back_btn)
    ImageButton backBtn;
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

        presenter.attach(this);
        presenter.setTextChangeObservable(RxSearchView.queryTextChanges(searchView));
        presenter.setBackBtnObservable(RxView.clicks(backBtn));

        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        citiesRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        searchView.onActionViewExpanded();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void showSuggestedCitiesList(List<City> suggestedList) {
        adapter.setSuggestedCitiesList(suggestedList);
        citiesRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSuggestedCitiesList() {
        citiesRecyclerView.setVisibility(View.GONE);
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

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(City cityToAdd) {
        presenter.cityClicked(cityToAdd);
    }
}
