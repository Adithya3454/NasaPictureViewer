package com.nasa.nasapicturesviewer.common;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ActivityCompositionRoot mActivityCompositionRoot;
    private ControllerCompositionRoot mControllerCompositionRoot;

    public ActivityCompositionRoot getActivityCompositionRoot() {
        if (mActivityCompositionRoot == null) {
            NasaApplication nasaApplication = (NasaApplication) getApplication();
            mActivityCompositionRoot = new ActivityCompositionRoot(
                    nasaApplication.getCompositionRoot(),
                    this
            );
        }
        return mActivityCompositionRoot;
    }

    protected ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }

}
