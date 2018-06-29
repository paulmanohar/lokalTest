package com.lokal.lokaltest.view.iview;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lokal.lokaltest.presenter.ipresenter.IPresenter;

/**
 * Created by ADMIN on 28-06-2018.
 */

public interface IView {
    void showMessage(String message);

    void showMessage(int resId);

    void showProgressbar();

    void dismissProgressbar();

    FragmentActivity getActivity();

    void showSnackBar(String message);

    void showSnackBar(@NonNull View view, String message);

    void showNetworkMessage();

    void bindPresenter(IPresenter iPresenter);
}
