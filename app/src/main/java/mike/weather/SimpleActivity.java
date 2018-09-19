package mike.weather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SimpleActivity extends AppCompatActivity implements SimpleActivityContract.View {

    @BindView(R.id.cites_recycler_view) RecyclerView mCitiesRecyclerView;
    private SimpleActivityContract.Presenter mPresenter;
    private CitiesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_activity);
        ButterKnife.bind(this);

        mAdapter = new CitiesAdapter();
        mCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCitiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mCitiesRecyclerView.setAdapter(mAdapter);

        mPresenter = new SimpleActivityPresenter();
        mPresenter.attach(this);
        mPresenter.refreshWeatherList();
    }

    @Override
    public void showCitiesList(List<WeatherInfo> citiesList) {
        mAdapter.setmCitiesList(citiesList);
    }
}