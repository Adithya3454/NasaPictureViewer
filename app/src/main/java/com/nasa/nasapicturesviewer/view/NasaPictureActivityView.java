package com.nasa.nasapicturesviewer.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.MainThread;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NasaPictureActivityView implements NasaPictureListAdapter.NasaPictureCLickListener {

    private final RecyclerView mRecyclerNasaPictures;
    private final ViewMvcFactory viewMvcFactory;
    private List<NasaPicture> nasaPictureList;
    private View mRootView;

    public NasaPictureActivityView(LayoutInflater inflater, ViewGroup parentViewGroup, ViewMvcFactory viewMvcFactory) {
        this.viewMvcFactory = viewMvcFactory;
        setRootView(inflater.inflate(R.layout.activity_main, parentViewGroup));
        ImageView linearList = mRootView.findViewById(R.id.linear_list);
        ImageView gridList = mRootView.findViewById(R.id.grid_list);
        mRecyclerNasaPictures = mRootView.findViewById(R.id.recycler_view);
        linearList.setOnClickListener(v -> switchListView("linear"));
        gridList.setOnClickListener(v -> switchListView("grid"));
    }

    @MainThread
    public void setLinearRecyclerView(List<NasaPicture> nasaPictureList){
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(this, viewMvcFactory, NasaPictureListAdapter.LIST_ITEM_LINEAR);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
    }


    public void setGridRecyclerView(List<NasaPicture> nasaPictureList){
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new GridLayoutManager(getRootView().getContext(), 3));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(this, viewMvcFactory, NasaPictureListAdapter.LIST_ITEM_GRID);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
    }


    private void switchListView(String listStyle) {
        switch (listStyle){
            case "linear":
                setLinearRecyclerView(nasaPictureList);
                break;
            case "grid":
                setGridRecyclerView(nasaPictureList);
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
