package com.nasa.nasapicturesviewer.adapters;

import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListItemViewMvcImpl;
import com.nasa.nasapicturesviewer.common.views.ViewMvcFactory;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListViewMvc;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for displaying all nasa pictures
 */
public class NasaPictureListAdapter extends RecyclerView.Adapter<NasaPictureListAdapter.MyViewHolder> {

    public static final int LIST_ITEM_LINEAR = 0;
    public static final int LIST_ITEM_GRID = 1;
    private final int LIST_ITEM_TYPE;

    private List<NasaPicture> nasaPictureList;
    private final ViewMvcFactory viewMvcFactory;
    private NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureClickListener;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final NasaPictureListItemViewMvcImpl mViewMvc;

        public MyViewHolder(NasaPictureListItemViewMvcImpl viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }

    }

    public NasaPictureListAdapter(ViewMvcFactory viewMvcFactory, NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureClickListener, int LIST_ITEM_TYPE) {
        this.nasaPictureClickListener = nasaPictureClickListener;
        this.viewMvcFactory = viewMvcFactory;
        this.LIST_ITEM_TYPE = LIST_ITEM_TYPE;
    }

    public void bindPictures(ArrayList<NasaPicture> nasaPicturesList) {
        this.nasaPictureList = nasaPicturesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NasaPictureListItemViewMvcImpl nasaPictureListItemViewMvcImpl = viewMvcFactory.getNasaPictureListItemView(parent, LIST_ITEM_TYPE);
        nasaPictureListItemViewMvcImpl.registerListener(nasaPictureClickListener);
        return new MyViewHolder(nasaPictureListItemViewMvcImpl);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mViewMvc.bindNasaPicture(nasaPictureList.get(position), LIST_ITEM_TYPE);
    }

    @Override
    public int getItemCount() {
        return nasaPictureList.size();
    }

}
