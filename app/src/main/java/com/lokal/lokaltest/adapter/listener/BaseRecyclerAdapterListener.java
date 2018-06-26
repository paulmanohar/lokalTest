package com.lokal.lokaltest.adapter.listener;

/**
 * Created by ADMIN on 25-06-2018.
 */

public interface BaseRecyclerAdapterListener<T> {

    void onClickItem(int position, T data);

}
