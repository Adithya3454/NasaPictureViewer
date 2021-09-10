package com.nasa.nasapicturesviewer.adapters;

import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.NasaPictureListItemView;
import com.nasa.nasapicturesviewer.view.ViewMvcFactory;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NasaPictureListAdapter extends RecyclerView.Adapter<NasaPictureListAdapter.MyViewHolder> {

    public static final int LIST_ITEM_LINEAR = 0;
    public static final int LIST_ITEM_GRID = 1;
    private int LIST_ITEM_TYPE;

    private List<NasaPicture> nasaPictureList;
    private final ViewMvcFactory viewMvcFactory;

    public interface NasaPictureCLickListener {
        void onPictureClicked(NasaPicture nasaPicture);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final NasaPictureListItemView mViewMvc;

        public MyViewHolder(NasaPictureListItemView viewMvc) {
            super(viewMvc.getRootView());
            mViewMvc = viewMvc;
        }

    }

    public NasaPictureListAdapter(NasaPictureCLickListener nasaPictureCLickListener, ViewMvcFactory viewMvcFactory, int LIST_ITEM_TYPE) {
        this.viewMvcFactory = viewMvcFactory;
        this.LIST_ITEM_TYPE = LIST_ITEM_TYPE;
    }

    public void bindPictures(ArrayList<NasaPicture> nasaPicturesList){
        this.nasaPictureList = nasaPicturesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NasaPictureListItemView nasaPictureListItemView = viewMvcFactory.getNasaPictureListItemView(parent, LIST_ITEM_TYPE);
//        nasaPictureListItemView.registerListener(this);
        return new MyViewHolder(nasaPictureListItemView);
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
