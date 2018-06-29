package com.lokal.lokaltest.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lokal.lokaltest.R;
import com.lokal.lokaltest.adapter.ShowImageAdapter;
import com.lokal.lokaltest.presenter.ShowImageFragPresenter;
import com.lokal.lokaltest.presenter.ipresenter.IShowImageFragPresenter;
import com.lokal.lokaltest.view.iview.IShowImageFragView;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class ShowImageFragFragment extends BaseFragment implements View.OnClickListener,IShowImageFragView {

    private RecyclerView mRecyclerView;
    private IShowImageFragPresenter iShowImageFragPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_download, container, false);
        bindFragment(rootView);
        iShowImageFragPresenter = new ShowImageFragPresenter(this);
        iShowImageFragPresenter.onCreatePresenter(getArguments());
        return rootView;
    }

    private void bindFragment(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.imageList_recycler);

    }

    @Override
    public void setShowImageAdapter(ShowImageAdapter showImageAdapter) {
        mRecyclerView.setAdapter(showImageAdapter);
    }


    @Override
    public void onClick(View v) {

    }
}
