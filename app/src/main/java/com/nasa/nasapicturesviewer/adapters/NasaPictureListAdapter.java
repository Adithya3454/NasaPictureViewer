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

    private List<NasaPicture> nasaPictureList;
    private ViewMvcFactory viewMvcFactory;

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
        this.viewMvcFactory = viewMvcFactory;
    }

    public void bindPictures(ArrayList<NasaPicture> nasaPicturesList){
        this.nasaPictureList = nasaPicturesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NasaPictureListItemView nasaPictureListItemView = viewMvcFactory.getNasaPictureListItemView(parent);
//        nasaPictureListItemView.registerListener(this);
        return new MyViewHolder(nasaPictureListItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mViewMvc.bindNasaPicture(nasaPictureList.get(position));
    }

    @Override
    public int getItemCount() {
        return nasaPictureList.size();
    }

}
