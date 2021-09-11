package com.nasa.nasapicturesviewer.common;

import com.nasa.nasapicturesviewer.common.dependencyinjection.ActivityCompositionRoot;
import com.nasa.nasapicturesviewer.common.dependencyinjection.ControllerCompositionRoot;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

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

    public ControllerCompositionRoot getCompositionRoot() {
        if (mControllerCompositionRoot == null) {
            mControllerCompositionRoot = new ControllerCompositionRoot(getActivityCompositionRoot());
        }
        return mControllerCompositionRoot;
    }

}
