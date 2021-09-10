package com.nasa.nasapicturesviewer.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ViewMvcFactory {

    private final LayoutInflater inflater;

    public ViewMvcFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public NasaPictureListItemView getNasaPictureListItemView(ViewGroup parent, int LIST_ITEM_TYPE) {
        return new NasaPictureListItemView(inflater, parent, LIST_ITEM_TYPE);
    }
}
