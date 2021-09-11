package com.nasa.nasapicturesviewer.common.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailActivityView;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailView;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListActivityView;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListItemViewMvcImpl;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListViewMvc;

public class ViewMvcFactory {

    private final LayoutInflater inflater;

    public ViewMvcFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public NasaPictureListActivityView getNasaPictureActivityView(ViewGroup parent, NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener){
        return new NasaPictureListActivityView(inflater, parent, this, nasaPictureCLickListener);
    }

    public NasaPictureListItemViewMvcImpl getNasaPictureListItemView(ViewGroup parent, int LIST_ITEM_TYPE) {
        return new NasaPictureListItemViewMvcImpl(inflater, parent, LIST_ITEM_TYPE);
    }

    public NasaPictureDetailView getNasaPictureDetailView(ViewGroup container) {
        return new NasaPictureDetailView(inflater, container);
    }

    public NasaPictureDetailActivityView getNasaPictureDetailActivityView(ViewGroup container){
        return new NasaPictureDetailActivityView(inflater, container);
    }
}
