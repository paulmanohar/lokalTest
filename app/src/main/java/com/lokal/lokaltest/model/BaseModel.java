package com.lokal.lokaltest.model;

import com.lokal.lokaltest.webservice.ApiClient;
import com.lokal.lokaltest.model.listener.IBaseModelListener;
import com.lokal.lokaltest.model.response.ImageResponse;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by ADMIN on 25-06-2018.
 */

abstract class BaseModel<T> implements IBaseModelListener<T> {

    private String TAG = getClass().getSimpleName();
    private long mCurrentTaskId = -1;
    private Callback<T> baseModelCallBackListener = new Callback<T>() {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {

            if (response.isSuccessful() && response.body() != null) {
                T result = response.body();
                onSuccessfulApi(mCurrentTaskId, result);
            } else {
                Converter<ResponseBody, T> converter =
                        ApiClient.getClient()
                                .responseBodyConverter(ImageResponse.class, new Annotation[0]);
                onFailureApi(mCurrentTaskId);
            }

        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {

            onFailureApi(mCurrentTaskId);
        }
    };

    BaseModel() {
    }

    void enQueueTask(long taskId, Call<T> tCall) {
        this.mCurrentTaskId = taskId;
        tCall.enqueue(baseModelCallBackListener);
    }
}
