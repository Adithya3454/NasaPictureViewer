package com.nasa.nasapicturesviewer.view.List;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPictureListAdapter;
import com.nasa.nasapicturesviewer.common.views.BaseViewMvc;
import com.nasa.nasapicturesviewer.common.views.ViewMvcFactory;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NasaPictureListActivityView extends BaseViewMvc {

    private static final String LOG_TAG = NasaPictureListActivityView.class.getSimpleName();
    private final NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener;
    private final RecyclerView mRecyclerNasaPictures;
    private final ViewMvcFactory viewMvcFactory;
    private List<NasaPicture> nasaPictureList;

    public NasaPictureListActivityView(LayoutInflater inflater, ViewGroup parentViewGroup, ViewMvcFactory viewMvcFactory, NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener) {
        this.viewMvcFactory = viewMvcFactory;
        this.nasaPictureCLickListener = nasaPictureCLickListener;
        setRootView(inflater.inflate(R.layout.activity_main, parentViewGroup, false));
        ImageView linearList = getRootView().findViewById(R.id.linear_list);
        ImageView gridList = getRootView().findViewById(R.id.grid_list);
        mRecyclerNasaPictures = getRootView().findViewById(R.id.recycler_view);
        linearList.setOnClickListener(v -> switchListView("linear"));
        gridList.setOnClickListener(v -> switchListView("grid"));
    }

    public void setLinearRecyclerView(List<NasaPicture> nasaPictureList) {
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(viewMvcFactory, nasaPictureCLickListener,NasaPictureListAdapter.LIST_ITEM_LINEAR);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
    }


    public void setGridRecyclerView(List<NasaPicture> nasaPictureList) {
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new GridLayoutManager(getRootView().getContext(), 3));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(viewMvcFactory, nasaPictureCLickListener, NasaPictureListAdapter.LIST_ITEM_GRID);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
    }


    private void switchListView(String listStyle) {
        switch (listStyle) {
            case "linear":
                setLinearRecyclerView(nasaPictureList);
                break;
            case "grid":
                setGridRecyclerView(nasaPictureList);
                break;
        }
    }
}
