package com.example.jhordan.people_mvvm.recyclerAdapter;

import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

import java.lang.ref.WeakReference;

/**
 * Created by TVG on 7/30/2016.
 */
public class WeakOnListChangedCallback extends ObservableList.OnListChangedCallback {

    private final WeakReference<RecyclerView.Adapter> mWeakAdapter;

    public WeakOnListChangedCallback(RecyclerView.Adapter adapter) {
        mWeakAdapter = new WeakReference<>(adapter);
    }

    @Override
    public void onChanged(ObservableList sender) {
        RecyclerView.Adapter adapter = mWeakAdapter.get();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = mWeakAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = mWeakAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }
    }

    @Override
    public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
        RecyclerView.Adapter adapter = mWeakAdapter.get();
        if (adapter != null) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    @Override
    public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
        RecyclerView.Adapter adapter = mWeakAdapter.get();
        if (adapter != null) {
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }

}