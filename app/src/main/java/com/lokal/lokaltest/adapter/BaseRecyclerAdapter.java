package com.lokal.lokaltest.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lokal.lokaltest.adapter.viewHolder.BaseViewHolder;

import java.util.List;

/**
 * Created by ADMIN on 25-06-2018.
 */

public abstract class BaseRecyclerAdapter<T, V extends BaseViewHolder> extends RecyclerView.Adapter<V> {

    public List<T> data;
    protected String TAG = getClass().getSimpleName();

    public BaseRecyclerAdapter(List<T> data) {
        this.data = data;
//        Log.d(TAG, "data size: " + data.size());
    }

    @Override
    public void onBindViewHolder(@NonNull V holder, int position) {
        holder.setData(getItem(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private T getItem(int position) throws IndexOutOfBoundsException {
        return data.get(position);
    }

    public void addItem(T object) {
        data.add(object);
        notifyItemInserted(data.indexOf(object));
    }

    public void removeItem(int position) throws IndexOutOfBoundsException {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void resetItems(List<T> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void replaceItems(List<T> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> data) {
        if (data != null) {
            int startRange = (this.data.size() - 1) > 0 ? data.size() - 1 : 0;
            this.data.addAll(data);
            notifyItemRangeChanged(startRange, data.size());
        }
    }


}
