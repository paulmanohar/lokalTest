package com.lokal.lokaltest.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.model.DownloadImageModel;
import com.lokal.lokaltest.model.ImageModel;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by ADMIN on 25-06-2018.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder>{

    private List<ImageResponse> imageResponses;
    private Activity context;
    private int resource;
    private ImageModel imageModel;
    private DownloadImageModel downloadImageModel;
    private DownloadManager downloadManager;
    private long enqueue;
    Bitmap bitmap = null;

    public void setImageResponses(List<ImageResponse> imageResponses) {
        if(imageResponses !=null) {
            this.imageResponses = imageResponses;
            notifyDataSetChanged();
        }
    }

    public ImageListAdapter(Activity context, int resource, List<ImageResponse> imageResponses, DownloadImageModel downloadImageModel,DownloadManager downloadManager) {
        this.resource = resource;
        this.context = context;
        this.imageResponses = imageResponses;
        this.downloadImageModel = downloadImageModel;
        this.downloadManager = downloadManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_image, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ImageResponse data = imageResponses.get(position);
        if(data !=null){

            holder.file_name.setText(data.getFilename());
            downloadImageModel.downloadFile(data.getPostUrl()+"/download",holder.image,data.getFilename());

            holder.download_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri uri = Uri.parse(data.getPostUrl()+"/download");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    enqueue = downloadManager.enqueue(request);

                    File file;
                    String path = Environment.getExternalStorageDirectory().toString();
                    file = new File(path+"/PicSum",data.getFilename());

                    try{

                        OutputStream stream = null;

                        stream = new FileOutputStream(file);

                        bitmap.compress(Bitmap.CompressFormat.JPEG,20,stream);

                        stream.flush();

                        stream.close();

                    }catch (IOException e) // Catch the exception
                    {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView file_name;
        private ImageView image;
        private Button download_button;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            file_name = itemView.findViewById(R.id.file_name);
            download_button = itemView.findViewById(R.id.download_button);

        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncGettingBitmapFromUrl extends AsyncTask<String,String,Bitmap>{

        private ImageView imageView;

        AsyncGettingBitmapFromUrl(ImageView imageView){
            this.imageView = imageView;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap compressedBitmap = null;
            try{
                URL url = new URL(strings[0]);
                bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);
                byte[] byteArray = stream.toByteArray();
                compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                imageView.setImageBitmap(compressedBitmap);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return compressedBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){

        }
    }
}
