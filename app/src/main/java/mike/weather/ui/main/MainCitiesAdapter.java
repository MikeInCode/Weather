package mike.weather.ui.main;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import mike.weather.R;
import mike.weather.data.model.City;
import mike.weather.ui.base.BaseAdapter;
import mike.weather.ui.base.BaseViewHolder;
import mike.weather.ui.base.OnItemClickListener;

public class MainCitiesAdapter extends BaseAdapter<City, MainCitiesAdapter.ViewHolder> {

    @Inject
    public MainCitiesAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.main_cities_list_item, parent));
    }

    public static class ViewHolder extends BaseViewHolder<City> {
        @BindView(R.id.city_name)
        TextView cityName;
        @BindView(R.id.country_name)
        TextView countryName;
        @BindView(R.id.current_temp)
        TextView currentTemp;
        @BindView(R.id.weather_icon)
        ImageView weatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(City item, OnItemClickListener listener) {
            cityName.setText(item.getCityName());
            countryName.setText(item.getCountryName());
            currentTemp.setText(item.getCurrentConditions().getTempCelsius());
            weatherIcon.setImageResource(item.getCurrentConditions().getIcon());
            itemView.setOnClickListener(l -> listener.onItemClick(getAdapterPosition()));
        }
    }
}
