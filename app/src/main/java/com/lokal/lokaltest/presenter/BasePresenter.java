package com.lokal.lokaltest.presenter;

import android.content.Intent;

import com.lokal.lokaltest.presenter.ipresenter.IPresenter;
import com.lokal.lokaltest.view.iview.IView;

/**
 * Created by ADMIN on 28-06-2018.
 */

abstract class BasePresenter implements IPresenter {

    protected String TAG = getClass().getSimpleName();

    private IView iView;

    public BasePresenter(IView iView) {
        this.iView = iView;
        iView.bindPresenter(this);
    }

    @Override
    public void onStartPresenter() {

    }

    @Override
    public void onStopPresenter() {

    }

    @Override
    public void onPausePresenter() {

    }

    @Override
    public void onResumePresenter() {

    }

    @Override
    public void onDestroyPresenter() {

    }

    @Override
    public void onActivityResultPresenter(int requestCode, int resultCode, Intent data) {

    }

    protected String getStringRes(int res){
        return iView.getActivity().getString(res);
    }

    protected int getIntegerRes(int res){
        return iView.getActivity().getResources().getInteger(res);
    }
}
