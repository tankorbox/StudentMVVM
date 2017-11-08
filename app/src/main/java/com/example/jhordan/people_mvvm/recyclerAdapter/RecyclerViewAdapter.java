package com.example.jhordan.people_mvvm.recyclerAdapter;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.example.jhordan.people_mvvm.bindingAdapter.AdapterObserver;

import java.util.List;


/**
 * Created by TVG on 7/30/2016.
 */
@SuppressWarnings("unchecked")
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterObserver<T> {

    private ObservableList<T> items;
    private final WeakOnListChangedCallback callback;

    public RecyclerViewAdapter() {
        callback = new WeakOnListChangedCallback(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (items != null) {
            items.removeOnListChangedCallback(callback);
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public void setItems(@Nullable List<T> items) {
        if (this.items == items) {
            return;
        }
        if (this.items != null && !this.items.isEmpty()) {
            this.items.clear();
            this.items.removeOnListChangedCallback(callback);
            notifyDataSetChanged();
        }
        if (items instanceof ObservableList) {
            this.items = (ObservableList<T>) items;
            notifyDataSetChanged();
            this.items.addOnListChangedCallback(callback);
        } else if (items != null) {
            this.items = new ObservableArrayList<>();
            this.items.addAll(items);
            notifyDataSetChanged();
            this.items.addOnListChangedCallback(callback);
        } else {
            this.items = null;
        }
    }

    @Override
    public List<T> getItems() {
        return items;
    }

    @BindingAdapter(value = {"items"})
    public static <T> void setAdapter(RecyclerView recyclerView, List<T> items) {
        if (recyclerView.getAdapter() instanceof AdapterObserver) {
            AdapterObserver adapter = (AdapterObserver) recyclerView.getAdapter();
            adapter.setItems(items);
        }
    }
}
