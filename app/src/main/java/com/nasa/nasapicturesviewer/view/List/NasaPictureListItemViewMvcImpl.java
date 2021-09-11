package com.nasa.nasapicturesviewer.view.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.views.BaseObservableViewMvc;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.squareup.picasso.Picasso;

import androidx.core.view.ViewCompat;

import static com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter.LIST_ITEM_GRID;

public class NasaPictureListItemViewMvcImpl extends BaseObservableViewMvc<NasaPictureListViewMvc.NasaPictureCLickListener>
        implements NasaPictureListViewMvc {

    private NasaPicture nasaPicture;
    private final ImageView imageView;
    private final TextView titleTextView;
    private final TextView descriptionTextView;


    public NasaPictureListItemViewMvcImpl(LayoutInflater layoutInflater, ViewGroup parent, int LIST_ITEM_TYPE) {
        setRootView(layoutInflater.inflate(R.layout.nasa_picture_linear_list_item, parent, false));
        imageView = findViewById(R.id.image);
        titleTextView = findViewById(R.id.title);
        descriptionTextView = findViewById(R.id.description);
        if (LIST_ITEM_TYPE == LIST_ITEM_GRID) {
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }
        getRootView().setOnClickListener(view -> {
            for (NasaPictureCLickListener listener : getListeners()) {
                listener.onPictureClicked(nasaPicture, imageView);
            }
        });
    }


    @Override
    public void bindNasaPicture(NasaPicture nasaPicture, int LIST_ITEM_TYPE) {
        this.nasaPicture = nasaPicture;
        titleTextView.setText(nasaPicture.getTitle());
        descriptionTextView.setText(nasaPicture.getExplanation());
        ViewCompat.setTransitionName(imageView, nasaPicture.getUrl());
        if (LIST_ITEM_TYPE == LIST_ITEM_GRID) {
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }
        Picasso.get().load(nasaPicture.getUrl()).into(imageView);
        getRootView().setOnClickListener(view -> {
            for (NasaPictureCLickListener listener : getListeners()) {
                listener.onPictureClicked(nasaPicture, imageView);
            }
        });
    }
}
