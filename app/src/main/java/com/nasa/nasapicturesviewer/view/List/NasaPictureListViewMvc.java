package com.nasa.nasapicturesviewer.view.List;

import android.view.View;

import com.nasa.nasapicturesviewer.model.NasaPicture;

public interface NasaPictureListViewMvc {

    public interface NasaPictureCLickListener {
        void onPictureClicked(NasaPicture nasaPicture, View clickedNasaPictureView);
    }

    void bindNasaPicture(NasaPicture nasaPicture, int LIST_ITEM_TYPE);
}
