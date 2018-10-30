package mike.weather.ui.search;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.SearchData;
import mike.weather.injection.module.SearchActivityModule;
import mike.weather.ui.base.BaseActivity;
import mike.weather.ui.base.OnItemClickListener;

public class SearchActivity extends BaseActivity implements SearchActivityContract.View,
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
        App.getAppComponent().plus(new SearchActivityModule()).inject(this);

        presenter.attach(this);
        presenter.setTextChangeObservable(RxSearchView.queryTextChanges(searchView));

        backBtn.setOnClickListener(l -> presenter.backBtnClicked());

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
    public void showSuggestedCitiesList(List<SearchData> suggestedList) {
        adapter.setList(suggestedList);
        citiesRecyclerView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideSuggestedCitiesList() {
        citiesRecyclerView.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showCityNotFoundMessage() {
        cityNotFoundMessage.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideCityNotFoundMessage() {
        cityNotFoundMessage.setVisibility(android.view.View.GONE);
    }

    @Override
    public void onItemClick(int position) {
        presenter.cityClicked(adapter.getItemAtPosition(position).getCity());
    }
}
