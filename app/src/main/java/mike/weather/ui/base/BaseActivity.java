package mike.weather.ui.base;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.R;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    @Nullable
    @BindView(R.id.last_update_date)
    TextView lastUpdateDate;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLastUpdateDate(String date) {
        lastUpdateDate.setText(date);
    }

    @Override
    public void goBack() {
        onBackPressed();
    }

    @Override
    public void goToApiWebsite() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.aerisweather.com")));
    }
}
