package com.nasa.nasapicturesviewer.common;

import android.content.Context;
import android.view.LayoutInflater;

import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;
import com.nasa.nasapicturesviewer.view.ViewMvcFactory;

import androidx.appcompat.app.AppCompatActivity;

public class ControllerCompositionRoot {

    private ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        mActivityCompositionRoot = activityCompositionRoot;
    }

    private AppCompatActivity getActivity() {
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
