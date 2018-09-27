package mike.weather.ui.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mike.weather.App;
import mike.weather.R;
import mike.weather.injection.module.SearchActivityModule;

public class SearchActivity extends AppCompatActivity implements SearchActivityContract.View {

    @BindView(R.id.search_view)
    SearchView searchView;
    @Inject
    SearchActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        App.getAppComponent().plus(new SearchActivityModule()).inject(this);

        searchView.onActionViewExpanded();

        presenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @OnClick(R.id.back_btn)
    public void backBtn() {
        presenter.backBtClicked();
    }
}
