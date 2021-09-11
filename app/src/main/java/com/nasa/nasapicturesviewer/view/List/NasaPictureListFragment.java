package com.nasa.nasapicturesviewer.view.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.data.NasaPicturesDataContract;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailParentFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

public class NasaPictureListFragment extends Fragment implements NasaPictureListViewMvc.NasaPictureCLickListener, NasaPicturesDataContract.NasaPicturesLoadingFinishedListener {

    private static final String LOG_TAG = NasaPictureListFragment.class.getSimpleName();
    private NasaPictureListViewMvc.NasaPictureCLickListener nasaPictureCLickListener;
    private NasaPictureListActivityView nasaPictureActivityView;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public static NasaPictureListFragment newInstance() {
        Bundle args = new Bundle();
        NasaPictureListFragment fragment = new NasaPictureListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseActivity activity = (BaseActivity) getActivity();
        nasaPictureActivityView = activity.getCompositionRoot().getViewMvcFactory().getNasaPictureActivityView(container, this);
        return nasaPictureActivityView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseActivity activity = (BaseActivity) getActivity();
        activity.getCompositionRoot().getNasaPictureDataContract().getPicturesForNasaGallery(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NasaPictureListViewMvc.NasaPictureCLickListener)
            nasaPictureCLickListener = (NasaPictureListViewMvc.NasaPictureCLickListener) context;
    }

    @Override
    public void onPictureClicked(NasaPicture nasaPicture, View clickedNasaPictureView) {
//        nasaPictureActivityView.prepareViewForFragmentChange();
//        nasaPictureCLickListener.onPictureClicked(nasaPicture, clickedNasaPictureView);
        Log.i(LOG_TAG, ViewCompat.getTransitionName(clickedNasaPictureView));
        getParentFragmentManager()
                .beginTransaction()
                .addSharedElement(clickedNasaPictureView, ViewCompat.getTransitionName(clickedNasaPictureView))
                .addToBackStack("TAG1")
                .replace(R.id.content, NasaPictureDetailParentFragment.newInstance())
                .commit();
    }

    @Override
    public void onCompletion(List<NasaPicture> nasaPictureList) {
        mHandler.post(() -> nasaPictureActivityView.setGridRecyclerView(nasaPictureList));
    }

    @Override
    public void onFailure(String error) {

    }
}
