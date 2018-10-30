package mike.weather.ui.search;

import android.support.annotation.NonNull;
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
import mike.weather.data.model.SearchData;
import mike.weather.ui.base.BaseAdapter;
import mike.weather.ui.base.BaseViewHolder;
import mike.weather.ui.base.OnItemClickListener;

public class SearchCitiesAdapter extends BaseAdapter<SearchData, SearchCitiesAdapter.ViewHolder> {

    @Inject
    public SearchCitiesAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.search_cities_list_item, parent));
    }

    public static class ViewHolder extends BaseViewHolder<SearchData> {
        @BindView(R.id.suggested_city)
        TextView suggestedCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(SearchData item, OnItemClickListener listener) {
            suggestedCity.setText(item.getCity().toString());
            itemView.setOnClickListener(l -> listener.onItemClick(getAdapterPosition()));
        }
    }
}
