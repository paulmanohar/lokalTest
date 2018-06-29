package com.lokal.lokaltest.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import com.lokal.lokaltest.adapter.ShowImageAdapter;
import com.lokal.lokaltest.view.iview.IShowImageFragView;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class ShowImageFragActivity extends BaseActivity implements IShowImageFragView, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setShowImageAdapter(ShowImageAdapter showImageAdapter) {

    }
}
