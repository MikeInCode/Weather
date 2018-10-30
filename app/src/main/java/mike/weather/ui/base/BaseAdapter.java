package mike.weather.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {

    private List<T> list;
    private OnItemClickListener listener;

    public BaseAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.onBindView(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public View inflate(int layout, ViewGroup parent) {
        return LayoutInflater
                .from(parent.getContext())
                .inflate(layout, parent, false);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public T getItemAtPosition(int position) {
        return list.get(position);
    }
}
