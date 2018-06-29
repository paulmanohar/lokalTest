package com.lokal.lokaltest.adapter.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.adapter.listener.BaseRecyclerAdapterListener;
import com.lokal.lokaltest.model.response.ImageResponse;
import com.squareup.picasso.Picasso;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class ShowImageViewHolder extends BaseViewHolder<ImageResponse> implements View.OnClickListener {

    private BaseRecyclerAdapterListener<ImageResponse> imageResponseAdapterListener;
    private TextView fileName;
    private Button downloadButton;
    private ImageView fileImage;

    public ShowImageViewHolder(View itemView, BaseRecyclerAdapterListener<ImageResponse> imageResponseAdapterListener) {
        super(itemView);
        this.imageResponseAdapterListener = imageResponseAdapterListener;
        bindHolder();

    }

    private void bindHolder() {
        fileImage = itemView.findViewById(R.id.image);
        fileName = itemView.findViewById(R.id.file_name);
        downloadButton = itemView.findViewById(R.id.download_button);
        downloadButton.setOnClickListener(this);
    }

    @Override
    protected void populateData() {
        if(data !=null){
            fileName.setText(data.getFilename());
            Picasso.with(fileImage.getContext()).load(data.getPostUrl()+"/download").resize(75,75).placeholder(R.drawable.ic_image_black_24dp).into(fileImage);
        }
    }

    @Override
    public void onClick(View v) {
        imageResponseAdapterListener.onClickItem(getAdapterPosition(),data);
    }
}
