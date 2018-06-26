package com.lokal.lokaltest.model.listener;

/**
 * Created by ADMIN on 26-06-2018.
 */

public interface IBaseModelListener<T> {

    void onSuccessfulApi(long taskId, T response);

    void onFailureApi(long taskId);
}
