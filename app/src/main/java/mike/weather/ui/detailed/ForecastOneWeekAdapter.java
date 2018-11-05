package mike.weather.ui.detailed;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import mike.weather.R;
import mike.weather.data.model.Forecast;
import mike.weather.ui.base.BaseAdapter;
import mike.weather.ui.base.BaseViewHolder;
import mike.weather.ui.base.OnItemClickListener;

public class ForecastOneWeekAdapter extends BaseAdapter<Forecast, ForecastOneWeekAdapter.ViewHolder> {

    @Inject
    public ForecastOneWeekAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.forecast_one_week_list_item, parent));
    }

    public static class ViewHolder extends BaseViewHolder<Forecast> {
        @BindView(R.id.day_of_week)
        TextView dayOfWeek;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.weather_icon)
        ImageView icon;
        @BindView(R.id.min_temp)
        TextView minTemp;
        @BindView(R.id.max_temp)
        TextView maxTemp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(Forecast item, OnItemClickListener listener) {
            dayOfWeek.setText(item.getDayOfWeek());
            date.setText(item.getDate());
            icon.setImageResource(item.getIcon());
            minTemp.setText(item.getMinTempCelsius());
            maxTemp.setText(item.getMaxTempCelsius());
        }
    }
}
