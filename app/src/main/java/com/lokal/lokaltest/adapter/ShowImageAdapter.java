package com.lokal.lokaltest.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.adapter.listener.BaseRecyclerAdapterListener;
import com.lokal.lokaltest.adapter.viewHolder.ShowImageViewHolder;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.util.List;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class ShowImageAdapter extends BaseRecyclerAdapter<ImageResponse,ShowImageViewHolder>{

    private BaseRecyclerAdapterListener imageResponseAdapterListener;
    private List<ImageResponse> data;

    public ShowImageAdapter(List<ImageResponse> data, BaseRecyclerAdapterListener<ImageResponse> imageResponseAdapterListener) {
        super(data);
        this.data = data;
        this.imageResponseAdapterListener = imageResponseAdapterListener;
    }

    public void setImageResponses(List<ImageResponse> data) {
        if(data !=null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return 50;
    }


    @NonNull
    @Override
    public ShowImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image,parent,false),imageResponseAdapterListener);
    }
}
