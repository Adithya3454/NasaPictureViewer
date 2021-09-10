package com.nasa.nasapicturesviewer.adapters;

import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.NasaPictureListItemView;
import com.nasa.nasapicturesviewer.view.ViewMvcFactory;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NasaPictureListAdapter extends RecyclerView.Adapter<NasaPictureListAdapter.MyViewHolder> {

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

    public NasaPictureListAdapter(NasaPictureCLickListener nasaPictureCLickListener, ViewMvcFactory viewMvcFactory) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
