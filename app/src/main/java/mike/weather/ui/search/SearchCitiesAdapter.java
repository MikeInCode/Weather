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
import mike.weather.data.model.City;
import mike.weather.ui.base.OnItemClickListener;

public class SearchCitiesAdapter extends RecyclerView.Adapter<SearchCitiesAdapter.ViewHolder> {

    private List<City> suggestedCitiesList;
    private OnItemClickListener onItemClickListener;

    @Inject
    public SearchCitiesAdapter() {
        suggestedCitiesList = new ArrayList<>();
    }

    public void setSuggestedCitiesList(List<City> suggestedCitiesList) {
        this.suggestedCitiesList = suggestedCitiesList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        City city = suggestedCitiesList.get(position);
        holder.suggestedCity.setText(city.toString());
        holder.itemView.setOnClickListener(l -> onItemClickListener.onItemClick(city));
    }

    @Override
    public int getItemCount() {
        return suggestedCitiesList.size();
    }
}
