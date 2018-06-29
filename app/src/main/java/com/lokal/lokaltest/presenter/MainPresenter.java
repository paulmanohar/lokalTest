package com.lokal.lokaltest.presenter;

import android.os.Bundle;

import com.lokal.lokaltest.presenter.ipresenter.IMainPresenter;
import com.lokal.lokaltest.view.iview.IMainView;
import com.lokal.lokaltest.view.iview.IView;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class MainPresenter extends BasePresenter implements IMainPresenter{

    private IMainView iMainView;


    public MainPresenter(IMainView iMainView) {
        super(iMainView);
        this.iMainView = iMainView;
    }

    @Override
    public void onCreatePresenter(Bundle bundle) {

    }
}
