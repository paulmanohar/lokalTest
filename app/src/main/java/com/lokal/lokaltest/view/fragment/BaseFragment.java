package com.lokal.lokaltest.view.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lokal.lokaltest.presenter.ipresenter.IPresenter;
import com.lokal.lokaltest.view.iview.IView;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class BaseFragment extends Fragment implements IView{

    protected String TAG = getClass().getSimpleName();

    @Override
    public void bindPresenter(IPresenter iPresenter) {

    }

    @Override
    public void showMessage(String message) {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showMessage(message);
    }

    @Override
    public void showMessage(int resId) {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showMessage(resId);
    }

    @Override
    public void showProgressbar() {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showProgressbar();
    }

    @Override
    public void dismissProgressbar() {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).dismissProgressbar();
    }

    @Override
    public void showSnackBar(String message) {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showSnackBar(message);
    }

    @Override
    public void showSnackBar(@NonNull View view, String message) {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showSnackBar(view,message);
    }

    @Override
    public void showNetworkMessage() {
        assert ((IView) getActivity()) != null;
        ((IView) getActivity()).showNetworkMessage();
    }


}
