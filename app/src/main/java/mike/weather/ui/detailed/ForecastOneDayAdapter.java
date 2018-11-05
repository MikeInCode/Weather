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

public class ForecastOneDayAdapter extends BaseAdapter<Forecast, ForecastOneDayAdapter.ViewHolder> {

    @Inject
    public ForecastOneDayAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.forecast_one_day_list_item, parent));
    }

    public static class ViewHolder extends BaseViewHolder<Forecast> {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.weather_icon)
        ImageView icon;
        @BindView(R.id.current_temp)
        TextView temp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(Forecast item, OnItemClickListener listener) {
            time.setText(item.getTime());
            icon.setImageResource(item.getIcon());
            temp.setText(item.getTempCelsius());
        }
    }
}
