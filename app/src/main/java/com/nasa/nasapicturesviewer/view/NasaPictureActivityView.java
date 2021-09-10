package com.nasa.nasapicturesviewer.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NasaPictureActivityView implements NasaPictureListAdapter.NasaPictureCLickListener {

    private RecyclerView mRecyclerNasaPictures;
    private FrameLayout recyclerViewHolder;
    private ImageView linearList, gridList;
    private ViewMvcFactory viewMvcFactory;
    private View mRootView;

    public NasaPictureActivityView(LayoutInflater inflater, ViewGroup parentViewGroup, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.activity_main, parentViewGroup));
        linearList = mRootView.findViewById(R.id.linear_list);
        gridList = mRootView.findViewById(R.id.grid_list);
        recyclerViewHolder = mRootView.findViewById(R.id.recycler_view_holder);
        linearList.setOnClickListener(v -> switchListView("linear"));
        gridList.setOnClickListener(v -> switchListView("grid"));
    }

    public void setLinearRecyclerView(List<NasaPicture> nasaPictureList){
        RecyclerView recyclerView = new RecyclerView(getRootView().getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        recyclerView.setAdapter(new NasaPictureListAdapter(this, viewMvcFactory));
    }


    public void setGridRecyclerView(List<NasaPicture> nasaPictureList){}


    private void switchListView(String listStyle) {
        switch (listStyle){
            case "linear":
                break;
            case "grid":
                break;
        }
    }

    private void setRootView(View rootView) {
        mRootView = rootView;
    }

    public View getRootView() {
        return mRootView;
    }

    @Override
    public void onPictureClicked(NasaPicture nasaPicture) {

    }
}
