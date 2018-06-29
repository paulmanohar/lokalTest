package com.lokal.lokaltest.presenter;

import android.os.Bundle;

import com.lokal.lokaltest.adapter.ShowImageAdapter;
import com.lokal.lokaltest.adapter.listener.BaseRecyclerAdapterListener;
import com.lokal.lokaltest.model.ShowImageModel;
import com.lokal.lokaltest.model.listener.IBaseModelListener;
import com.lokal.lokaltest.model.response.ImageResponse;
import com.lokal.lokaltest.presenter.ipresenter.IShowImageFragPresenter;
import com.lokal.lokaltest.view.iview.IShowImageFragView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 28-06-2018.
 */

public class ShowImageFragPresenter extends BasePresenter implements IShowImageFragPresenter {

    private IShowImageFragView iShowImageFragView;
    private List<ImageResponse> imageResponseList = new ArrayList<>();
    private ShowImageAdapter mShowImageAdapter;
    private ShowImageModel mShowImageModel;

    private BaseRecyclerAdapterListener<ImageResponse> showImageAdapterListener = new BaseRecyclerAdapterListener<ImageResponse>() {
        @Override
        public void onClickItem(int position, ImageResponse data) {

        }
    };

    private IBaseModelListener<List<ImageResponse>> iBaseModelListener = new IBaseModelListener<List<ImageResponse>>() {
        @Override
        public void onSuccessfulApi(long taskId, List<ImageResponse> response) {
            imageResponseList.addAll(response);
        }

        @Override
        public void onFailureApi(long taskId) {

        }
    };

    public ShowImageFragPresenter(IShowImageFragView iShowImageFragView) {
        super(iShowImageFragView);
        this.iShowImageFragView = iShowImageFragView;
        mShowImageModel = new ShowImageModel(iBaseModelListener);
        mShowImageAdapter = new ShowImageAdapter(imageResponseList,showImageAdapterListener);
    }

    @Override
    public void onCreatePresenter(Bundle bundle) {

    }
}
