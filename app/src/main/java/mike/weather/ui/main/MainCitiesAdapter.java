package mike.weather.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.R;
import mike.weather.data.model.City;

public class MainCitiesAdapter extends RecyclerView.Adapter<MainCitiesAdapter.ViewHolder> {

    private List<City> mCitiesList;

    @Inject
    public MainCitiesAdapter() {
        mCitiesList = new ArrayList<>();
    }

    public void setmCitiesList(List<City> citiesList) {
        mCitiesList.addAll(citiesList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.city_name) TextView mCityName;
        @BindView(R.id.country_name) TextView mCountryName;
        @BindView(R.id.temperature) TextView mTemperature;
        @BindView(R.id.condition_icon) ImageView mCondition;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public MainCitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cities_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mCityName.setText(mCitiesList.get(position).getmCityName());
        //holder.mCountryName.setText(mCitiesList.get(position).getmCountryName());
        //holder.mTemperature.setText(mCitiesList.get(position).getmTemperature());
        //holder.mCondition.setImageResource(mCitiesList.get(position).getmConditionImage());
    }

    @Override
    public int getItemCount() {
        return mCitiesList.size();
    }
}
