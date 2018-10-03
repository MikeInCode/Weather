package mike.weather.ui.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mike.weather.R;
import mike.weather.data.remote.WeatherApi;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private List<WeatherApi.SearchCity> suggestedCitiesList;
    private onItemClickListener onItemClickListener;

    @Inject
    public SearchCitiesAdapter() {
        suggestedCitiesList = new ArrayList<>();
    }

    interface onItemClickListener {
        void onItemClick(WeatherApi.SearchCity searchCity);
    }

    public void setOnItemClickListener(SearchCitiesAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSuggestedCitiesList(List<WeatherApi.SearchCity> suggestedCitiesList) {
        this.suggestedCitiesList = suggestedCitiesList;
        notifyDataSetChanged();
    }

    public void clearSuggestedCitiesList() {
        suggestedCitiesList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.suggested_city)
        TextView suggestedCity;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public SearchCitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_cities_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherApi.SearchCity city = suggestedCitiesList.get(position);
        String suggestion = city.getName() + ", "
                + city.getArea().getId() + ", "
                + city.getCountry().getName();
        holder.suggestedCity.setText(suggestion);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestedCitiesList.size();
    }
}
