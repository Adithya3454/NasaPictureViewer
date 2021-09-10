package com.nasa.nasapicturesviewer.common;

import android.content.Context;

import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;

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

    public GetNasaPictureDataContractImpl getNasaPictureDataContract(Context context) {
        return compositionRoot.getNasaPicturesDataContract(context);
    }
}
