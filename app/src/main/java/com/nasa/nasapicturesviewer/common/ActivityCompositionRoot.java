package com.nasa.nasapicturesviewer.common;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCompositionRoot {

    private final CompositionRoot compositionRoot;
    private final AppCompatActivity mActivity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, AppCompatActivity mActivity) {
        this.compositionRoot = compositionRoot;
        this.mActivity = mActivity;
    }

    public AppCompatActivity getActivity() {
        return mActivity;
    }
}
