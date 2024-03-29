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

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * View for Activity (NasaPictureActivity) that displays all pictures
 */
public class NasaPictureListActivityView extends BaseViewMvc {

    private static final String LOG_TAG = NasaPictureListActivityView.class.getSimpleName();
    private final NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener;
    private final RecyclerView mRecyclerNasaPictures;
    private final ViewMvcFactory viewMvcFactory;
    private final ImageView linearList;
    private final ImageView gridList;
    private List<NasaPicture> nasaPictureList;

    public NasaPictureListActivityView(LayoutInflater inflater, ViewGroup parentViewGroup, ViewMvcFactory viewMvcFactory, NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener) {
        this.viewMvcFactory = viewMvcFactory;
        this.nasaPictureCLickListener = nasaPictureCLickListener;
        setRootView(inflater.inflate(R.layout.activity_main, parentViewGroup, false));
        linearList = getRootView().findViewById(R.id.linear_list);
        gridList = getRootView().findViewById(R.id.grid_list);
        mRecyclerNasaPictures = getRootView().findViewById(R.id.recycler_view);
        linearList.setOnClickListener(v -> switchListView("linear"));
        gridList.setOnClickListener(v -> switchListView("grid"));
    }

    /**
     * Shows a linear recycler view
     * @param nasaPictureList a list iof pictures to be displayed
     */
    public void setLinearRecyclerView(List<NasaPicture> nasaPictureList) {
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(viewMvcFactory, nasaPictureCLickListener,NasaPictureListAdapter.LIST_ITEM_LINEAR);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
        linearList.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.selected_list_style_background));
        gridList.setBackground(null);
    }


    /**
     * Shows a grid recycler view
     * @param nasaPictureList a list iof pictures to be displayed
     */
    public void setGridRecyclerView(List<NasaPicture> nasaPictureList) {
        this.nasaPictureList = nasaPictureList;
        mRecyclerNasaPictures.setLayoutManager(new GridLayoutManager(getRootView().getContext(), 3));
        NasaPictureListAdapter nasaPictureListAdapter = new NasaPictureListAdapter(viewMvcFactory, nasaPictureCLickListener, NasaPictureListAdapter.LIST_ITEM_GRID);
        mRecyclerNasaPictures.setAdapter(nasaPictureListAdapter);
        nasaPictureListAdapter.bindPictures((ArrayList<NasaPicture>) nasaPictureList);
        gridList.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.selected_list_style_background));
        linearList.setBackground(null);
    }


    /**
     * Switch the list style
     * @param listStyle select between linear and grid style recycler view
     */
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
