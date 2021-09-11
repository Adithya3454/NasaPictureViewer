package com.nasa.nasapicturesviewer.common.dependencyinjection;

import android.content.Context;

import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;

import androidx.fragment.app.FragmentActivity;

public class ActivityCompositionRoot {

    private final CompositionRoot compositionRoot;
    private final FragmentActivity mActivity;

    public ActivityCompositionRoot(CompositionRoot compositionRoot, FragmentActivity mActivity) {
        this.compositionRoot = compositionRoot;
        this.mActivity = mActivity;
    }

    public FragmentActivity getActivity() {
        return mActivity;
    }

    public GetNasaPictureDataContractImpl getNasaPictureDataContract(Context context) {
        return compositionRoot.getNasaPicturesDataContract(context);
    }
}
