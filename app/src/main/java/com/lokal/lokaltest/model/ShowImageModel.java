package com.lokal.lokaltest.model;

import com.lokal.lokaltest.webservice.ApiClient;
import com.lokal.lokaltest.webservice.ApiInterface;
import com.lokal.lokaltest.model.listener.IBaseModelListener;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.util.List;

/**
 * Created by ADMIN on 26-06-2018.
 */

public class ShowImageModel extends BaseModel<List<ImageResponse>> {

    private IBaseModelListener<List<ImageResponse>> iBaseModelListener;

    public ShowImageModel(IBaseModelListener<List<ImageResponse>> iBaseModelListener) {
        this.iBaseModelListener = iBaseModelListener;
    }

    @Override
    public void onSuccessfulApi(long taskId, List<ImageResponse> response) {
        iBaseModelListener.onSuccessfulApi(taskId,response);
    }

    @Override
    public void onFailureApi(long taskId) {
        iBaseModelListener.onFailureApi(taskId);
    }

    public void getImagesList() {
        long mCurrentTaskId = 1;
        enQueueTask(mCurrentTaskId, ApiClient.getClient().create(ApiInterface.class).getImageList());
    }
}
