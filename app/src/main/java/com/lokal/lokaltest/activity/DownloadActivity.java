package com.lokal.lokaltest.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.adapter.ImageListAdapter;
import com.lokal.lokaltest.library.Log;
import com.lokal.lokaltest.model.DownloadImageModel;
import com.lokal.lokaltest.model.ImageModel;
import com.lokal.lokaltest.model.listener.IBaseModelListener;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by ADMIN on 26-06-2018.
 */

public class DownloadActivity extends AppCompatActivity {


    private RecyclerView imageListRecyclerView;
    private ImageListAdapter imageListAdapter;
    private ImageModel imageModel;
    private DownloadImageModel downloadImageModel;
    List<ImageResponse> imageListResponses = new ArrayList<>();
    private DownloadManager downloadManager;
    private long enqueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        imageListRecyclerView = findViewById(R.id.imageList_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(false);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        imageListRecyclerView.setLayoutManager(layoutManager);

        downloadManager =(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

//        BroadcastReceiver receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String action = intent.getAction();
//                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//                    long downloadId = intent.getLongExtra(
//                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//                    DownloadManager.Query query = new DownloadManager.Query();
//                    query.setFilterById(enqueue);
//                    Cursor c = downloadManager.query(query);
//                    if (c.moveToFirst()) {
//                        int columnIndex = c
//                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
//                        if (DownloadManager.STATUS_SUCCESSFUL == c
//                                .getInt(columnIndex)) {
//
//
//                            String uriString = c
//                                    .getString(c
//                                            .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
//
//                            Uri a = Uri.parse(uriString);
//                            File d = new File(a.getPath()+"/PicSum");
//                            // copy file from external to internal will esaily avalible on net use google.
////                            view.setImageURI(a);
//                        }
//                    }
//                }
//            }
//        };
//
//        registerReceiver(receiver, new IntentFilter(
//                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        this.downloadImageModel = new DownloadImageModel(getFilesDir().toString());
        this.imageModel = new ImageModel(new IBaseModelListener<List<ImageResponse>>() {
            @Override
            public void onSuccessfulApi(long taskId, List<ImageResponse> response) {
                Log.d("length1", String.valueOf(response.size()));
                imageListResponses.addAll(response) ;
                if (imageListAdapter == null) {
                    imageListAdapter = new ImageListAdapter(getParent(), R.layout.card_image, imageListResponses,downloadImageModel,downloadManager);
                    Log.d("length", String.valueOf(imageListResponses.size()));
                    imageListRecyclerView.setAdapter(imageListAdapter);
                } else {
                    imageListAdapter.setImageResponses(imageListResponses);
                }
            }

            @Override
            public void onFailureApi(long taskId) {

            }
        });

        imageModel.getImagesList();

    }
}
