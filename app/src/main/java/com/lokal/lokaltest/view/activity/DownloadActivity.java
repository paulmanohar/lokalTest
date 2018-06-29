package com.lokal.lokaltest.view.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.adapter.ShowImageAdapter;
import com.lokal.lokaltest.adapter.listener.BaseRecyclerAdapterListener;
import com.lokal.lokaltest.library.Log;
import com.lokal.lokaltest.model.DownloadImageModel;
import com.lokal.lokaltest.model.ShowImageModel;
import com.lokal.lokaltest.model.listener.IBaseModelListener;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 26-06-2018.
 */

public class DownloadActivity extends AppCompatActivity {


    private RecyclerView imageListRecyclerView;
    private ShowImageModel showImageModel;
    private ShowImageAdapter showImageAdapter;
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
//        this.downloadImageModel = new DownloadImageModel(getFilesDir().toString());
        this.showImageModel = new ShowImageModel(new IBaseModelListener<List<ImageResponse>>() {
            @Override
            public void onSuccessfulApi(long taskId, List<ImageResponse> response) {
                Log.d("length1", String.valueOf(response.size()));
                imageListResponses.addAll(response) ;
                if (showImageAdapter == null) {
                    showImageAdapter = new ShowImageAdapter(imageListResponses, new BaseRecyclerAdapterListener<ImageResponse>() {
                        @Override
                        public void onClickItem(int position, ImageResponse data) {
//                            Toast.makeText(getBaseContext(),data.getPostUrl()+"/download",Toast.LENGTH_SHORT).show();
                            Uri uri = Uri.parse(data.getPostUrl()+"/download");
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setTitle("Downloading "+data.getFilename());
//                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/PicSum");
                            enqueue = downloadManager.enqueue(request);
                        }
                    });
                    Log.d("length", String.valueOf(imageListResponses.size()));
                    imageListRecyclerView.setAdapter(showImageAdapter);
                } else {
                    showImageAdapter.setImageResponses(imageListResponses);
                }
            }

            @Override
            public void onFailureApi(long taskId) {

            }
        });

        showImageModel.getImagesList();

    }
}
