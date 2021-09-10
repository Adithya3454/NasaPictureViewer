package com.nasa.nasapicturesviewer.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter;
import com.nasa.nasapicturesviewer.common.views.BaseObservableViewMvc;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.squareup.picasso.Picasso;

import static com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter.LIST_ITEM_GRID;

public class NasaPictureListItemView extends BaseObservableViewMvc<NasaPictureListAdapter.NasaPictureCLickListener> {

    private final ImageView imageView;
    private final TextView titleTextView;
    private final TextView descriptionTextView;


    public NasaPictureListItemView(LayoutInflater layoutInflater, ViewGroup parent, int LIST_ITEM_TYPE) {
        setRootView(layoutInflater.inflate(R.layout.nasa_picture_linear_list_item, parent, false));
        imageView = findViewById(R.id.image);
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        if (LIST_ITEM_TYPE == LIST_ITEM_GRID){
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }
    }

    public void bindNasaPicture(NasaPicture nasaPicture, int LIST_ITEM_TYPE) {
        titleTextView.setText(nasaPicture.getTitle());
        descriptionTextView.setText(nasaPicture.getExplanation());
        if (LIST_ITEM_TYPE == LIST_ITEM_GRID){
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }
        Picasso.get().load(nasaPicture.getUrl()).into(imageView);
    }
}
