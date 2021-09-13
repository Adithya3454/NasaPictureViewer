package com.nasa.nasapicturesviewer.common;

import android.content.Context;

import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailActivity;

import java.util.List;

public class ScreensNavigator {

    private final Context mContext;

    public ScreensNavigator(Context mContext) {
        this.mContext = mContext;
    }

    public void GoToNasaPictureDetailActivity(int selectedPicturePosition, List<NasaPicture> nasaPictureList){
        NasaPictureDetailActivity.start(mContext, selectedPicturePosition, nasaPictureList);
    }

}
