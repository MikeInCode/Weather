package mike.weather.ui.main;

import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
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

        swipeRefreshLayout.setColorSchemeResources(R.color.color_primary);
        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        citiesRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        citiesRecyclerView.setAdapter(adapter);
        initItemTouchHelper();
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
    public void deleteCityFromList(int position) {
        adapter.removeItem(position);
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

    private void initItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                presenter.itemSwipedToDelete(position, adapter.getItemAtPosition(position));
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                new RecyclerViewSwipeDecorator.Builder(MainActivity.this, c, recyclerView,
                        viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_delete))
                        .addActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();
            }
        }).attachToRecyclerView(citiesRecyclerView);
    }
}
