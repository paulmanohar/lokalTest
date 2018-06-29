package com.lokal.lokaltest.presenter.ipresenter;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ADMIN on 28-06-2018.
 */

public interface IPresenter {

    void onCreatePresenter(Bundle bundle);

    void onStartPresenter();

    void onStopPresenter();

    void onPausePresenter();

    void onResumePresenter();

    void onDestroyPresenter();

    void onActivityResultPresenter(int requestCode, int resultCode, Intent data);
}
