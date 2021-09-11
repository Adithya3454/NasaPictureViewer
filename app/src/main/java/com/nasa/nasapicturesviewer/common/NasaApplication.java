package com.nasa.nasapicturesviewer.common;

import android.app.Application;

import com.nasa.nasapicturesviewer.common.dependencyinjection.CompositionRoot;

public class NasaApplication extends Application {
    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
