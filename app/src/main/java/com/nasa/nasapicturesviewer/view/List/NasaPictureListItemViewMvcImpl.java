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

import static com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter.LIST_ITEM_GRID;
/**
 * View for list item that displays pictures/ detaols of the picture depending on the list item type
 */
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

    /**
     * Binds the list item in recyclerview with data
     * @param nasaPicture picture whose details have to be shown
     * @param LIST_ITEM_TYPE type of the list item
     */
    @Override
    public void bindNasaPicture(NasaPicture nasaPicture, int LIST_ITEM_TYPE) {
        this.nasaPicture = nasaPicture;
        titleTextView.setText(nasaPicture.getTitle());
        descriptionTextView.setText(nasaPicture.getExplanation());

        if (LIST_ITEM_TYPE == LIST_ITEM_GRID) {
            titleTextView.setVisibility(View.GONE);
            descriptionTextView.setVisibility(View.GONE);
        }

        Picasso.get().load(nasaPicture.getUrl()).placeholder(R.drawable.ic_nasa).into(imageView);
        getRootView().setOnClickListener(view -> {
            for (NasaPictureCLickListener listener : getListeners()) {
                listener.onPictureClicked(nasaPicture, imageView);
            }
        });
    }
}
