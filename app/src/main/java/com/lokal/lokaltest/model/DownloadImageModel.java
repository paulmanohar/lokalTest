package com.lokal.lokaltest.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lokal.lokaltest.webservice.ApiClient;
import com.lokal.lokaltest.webservice.ApiInterface;
import com.lokal.lokaltest.model.listener.IDownloadModelListener;

import java.io.ByteArrayOutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ADMIN on 26-06-2018.
 */

public class DownloadImageModel<ResponeBody> implements IDownloadModelListener<ResponseBody> {

    private IDownloadModelListener<ResponseBody> iDownloadModelListener;
    private long mCurrentTaskId = 1;
    private String fileRootPath = "";

    public DownloadImageModel(IDownloadModelListener<ResponseBody> iDownloadModelListener) {
        this.iDownloadModelListener = iDownloadModelListener;
    }

    public DownloadImageModel(String fileDir) {
         this.fileRootPath = fileDir;
    }

    @Override
    public void onSuccessfulApi(long taskId, ResponseBody response, ImageView image) {
        iDownloadModelListener.onSuccessfulApi(taskId,response,image);

    }

    @Override
    public void onFailureApi(long taskId) {
        iDownloadModelListener.onFailureApi(taskId);
    }

    public void downloadFile(String fileUrl,ImageView image,String fileName) {

        enQueueTask(mCurrentTaskId, ApiClient.getClient().create(ApiInterface.class).downloadFile(fileUrl),image,fileName);
    }

    private void enQueueTask(long taskId, Call<ResponseBody> tCall, final ImageView image, final String fileName) {
        this.mCurrentTaskId = taskId;
        tCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG,10,stream);
                        byte[] byteArray = stream.toByteArray();
                        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                        image.setImageBitmap(compressedBitmap);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
