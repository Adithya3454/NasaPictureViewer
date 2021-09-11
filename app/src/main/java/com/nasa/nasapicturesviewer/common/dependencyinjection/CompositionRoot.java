package com.nasa.nasapicturesviewer.common.dependencyinjection;

import android.content.Context;

import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;

public class CompositionRoot {

    public GetNasaPictureDataContractImpl getNasaPicturesDataContract(Context context) {
        return new GetNasaPictureDataContractImpl(context);
    }
}
