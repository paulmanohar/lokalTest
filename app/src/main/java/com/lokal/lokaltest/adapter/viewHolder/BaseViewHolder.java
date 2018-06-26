package com.lokal.lokaltest.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ADMIN on 25-06-2018.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public T data;
    String TAG = getClass().getSimpleName();

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(T data) {
        this.data = data;
        populateData();
    }

    protected abstract void populateData();
}
