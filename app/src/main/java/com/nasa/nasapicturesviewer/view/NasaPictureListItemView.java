package com.nasa.nasapicturesviewer.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter;
import com.nasa.nasapicturesviewer.common.views.BaseObservableViewMvc;
import com.nasa.nasapicturesviewer.model.NasaPicture;

public class NasaPictureListItemView extends BaseObservableViewMvc<NasaPictureListAdapter.NasaPictureCLickListener> {

    private NasaPicture nasaPicture;
    private final ImageView imageView;
    private final TextView titleTextView;
    private final TextView descriptionTextView;


    public NasaPictureListItemView(LayoutInflater layoutInflater, ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.nasa_picture_linear_list_item, parent, false));
        imageView = findViewById(R.id.image);
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
    }

    public void bindNasaPicture(NasaPicture nasaPicture) {
        this.nasaPicture = nasaPicture;
        titleTextView.setText(nasaPicture.getTitle());
        descriptionTextView.setText(nasaPicture.getExplanation());
    }
}
