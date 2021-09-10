package com.nasa.nasapicturesviewer.data;

import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

/**
 * interface for interactor
 */
public interface NasaPicturesDataContract {

    interface NasaPicturesLoadingFinishedListener{
        void onCompletion(List<NasaPicture> nasaPictureList);
        void onFailure(String error);
    }

    void getPicturesForNasaGallery(NasaPicturesLoadingFinishedListener nasaPicturesLoadingFinishedListener);
}


