package com.nasa.nasapicturesviewer.view.List;

import android.os.Bundle;

import com.nasa.nasapicturesviewer.common.BaseActivity;

public class NasaPictureListActivity extends BaseActivity {

    private static final String LOG_TAG = NasaPictureListActivity.class.getSimpleName();
    private NasaPictureListController nasaPictureListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nasaPictureListController = getCompositionRoot().getNasaPictureListController();
        NasaPictureListActivityView nasaPictureActivityView = getCompositionRoot().getViewMvcFactory().getNasaPictureActivityView(null, nasaPictureListController);
        nasaPictureListController.bindView(nasaPictureActivityView);
        setContentView(nasaPictureActivityView.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        nasaPictureListController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        nasaPictureListController.onStop();
    }
}
