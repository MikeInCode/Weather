package mike.weather.ui.detailed;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.R;
import mike.weather.data.model.Conditions;
import mike.weather.ui.base.BaseAdapter;
import mike.weather.ui.base.BaseViewHolder;
import mike.weather.ui.base.OnItemClickListener;

public class ForecastAdapter extends BaseAdapter<Conditions, ForecastAdapter.ForecastViewHolder> {

    @Inject
    public ForecastAdapter() {
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForecastViewHolder(inflate(R.layout.forecast_24hours_list_item, parent));
    }

    public static class ForecastViewHolder extends BaseViewHolder<Conditions> {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.weather_icon)
        ImageView icon;
        @BindView(R.id.current_temp)
        TextView temp;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(Conditions item, OnItemClickListener listener) {
            time.setText(item.getTime());
            icon.setImageResource(item.getIcon());
            temp.setText(item.getTempCelsius());
        }
    }
}
