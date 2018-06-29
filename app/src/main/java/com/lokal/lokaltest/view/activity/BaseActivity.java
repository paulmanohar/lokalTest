package com.lokal.lokaltest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.library.ExceptionTracker;
import com.lokal.lokaltest.presenter.ipresenter.IPresenter;
import com.lokal.lokaltest.view.CodeSnippet;
import com.lokal.lokaltest.view.iview.IView;
import com.lokal.lokaltest.view.widget.CustomProgressbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 28-06-2018.
 */

abstract class BaseActivity extends AppCompatActivity implements IView{

    protected String TAG = getClass().getSimpleName();
    private View mParentView;
    private IPresenter iPresenter;
    private CodeSnippet mCodeSnippet;
    private CustomProgressbar mCustomProgressbar;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs){
        mParentView = getWindow().getDecorView().findViewById(android.R.id.content);
        return super.onCreateView(name,context,attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (iPresenter != null) iPresenter.onStartPresenter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (iPresenter != null) iPresenter.onStopPresenter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (iPresenter != null) iPresenter.onPausePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (iPresenter != null) iPresenter.onResumePresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iPresenter != null) iPresenter.onDestroyPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (iPresenter != null) iPresenter.onActivityResultPresenter(requestCode, resultCode, data);
    }

    private CustomProgressbar getProgressBar() {
        if (mCustomProgressbar == null) {
            mCustomProgressbar = new CustomProgressbar(this);
        }
        return mCustomProgressbar;
    }

    public void bindPresenter(IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resId) {
        Toast.makeText(this,getString(resId),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressbar() {
        getProgressBar().show();
    }

    @Override
    public void dismissProgressbar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    getProgressBar().dismissProgress();
                } catch (Exception e) {
                    ExceptionTracker.track(e);
                }
            }
        });
    }

    @Override
    public FragmentActivity getActivity() {
        return BaseActivity.this;
    }

    @Override
    public void showSnackBar(String message) {
        if (mParentView != null) {
            Snackbar snackbar = Snackbar.make(mParentView, message, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.RED);
            snackbar.show();
        }
    }

    @Override
    public void showSnackBar(@NonNull View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void showNetworkMessage() {
        if (mParentView != null) {
            Snackbar snackbar = Snackbar.make(mParentView, R.string.error_no_network_found, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.RED);
            snackbar.setAction(R.string.action_settings, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCodeSnippet.showNetworkSettings();
                }
            });
            snackbar.show();
        }
    }
}
