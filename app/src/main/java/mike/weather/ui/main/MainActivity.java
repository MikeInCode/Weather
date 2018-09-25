package mike.weather.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.App;
import mike.weather.R;
import mike.weather.data.model.CityInfo;
import mike.weather.injection.module.MainActivityModule;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.cites_recycler_view)
    RecyclerView mCitiesRecyclerView;
    @Inject
    MainActivityContract.Presenter mPresenter;
    @Inject
    CitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        App.getAppComponent().plus(new MainActivityModule()).inject(this);

        mCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCitiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mCitiesRecyclerView.setAdapter(mAdapter);

        mPresenter.attach(this);
        mPresenter.refreshWeatherList();
    }

    @Override
    public void showCitiesList(List<CityInfo> citiesList) {
        mAdapter.setmCitiesList(citiesList);
    }
}
