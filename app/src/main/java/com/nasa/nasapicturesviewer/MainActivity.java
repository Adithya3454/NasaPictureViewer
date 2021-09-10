package com.nasa.nasapicturesviewer;

import android.os.Bundle;

import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.view.NasaPictureActivityView;

public class MainActivity extends BaseActivity {

    private NasaPictureActivityView nasaPictureActivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nasaPictureActivityView = new NasaPictureActivityView(getLayoutInflater(), null, getCompositionRoot().getViewMvcFactory());
        setContentView(nasaPictureActivityView.getRootView());
        super.onCreate(savedInstanceState);
    }
}