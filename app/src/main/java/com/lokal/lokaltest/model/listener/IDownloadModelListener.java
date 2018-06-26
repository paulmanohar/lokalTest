package com.lokal.lokaltest.model.listener;

import android.widget.ImageView;

/**
 * Created by ADMIN on 26-06-2018.
 */

public interface IDownloadModelListener<T> {
    void onSuccessfulApi(long taskId, T response, ImageView image);

    void onFailureApi(long taskId);
}
