package com.nasa.nasapicturesviewer.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import com.nasa.nasapicturesviewer.common.views.ViewMvcFactory;
import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;

import androidx.fragment.app.FragmentActivity;

public class ControllerCompositionRoot {

    private ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        mActivityCompositionRoot = activityCompositionRoot;
    }

    private FragmentActivity getActivity() {
        return mActivityCompositionRoot.getActivity();
    }

    private Context getContext() {
        return getActivity();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public GetNasaPictureDataContractImpl getNasaPictureDataContract(){
        return mActivityCompositionRoot.getNasaPictureDataContract(getContext());
    }

}
