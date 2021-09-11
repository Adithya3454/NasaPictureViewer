package com.nasa.nasapicturesviewer.view.Detail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.data.NasaPicturesDataContract;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

public class NasaPictureDetailParentFragment extends Fragment implements NasaPicturesDataContract.NasaPicturesLoadingFinishedListener {

    private NasaPictureDetailActivityView nasaPictureDetailActivityView;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private BaseActivity baseActivity;


    public static NasaPictureDetailParentFragment newInstance() {
//        Bundle args = new Bundle();

        NasaPictureDetailParentFragment fragment = new NasaPictureDetailParentFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        setSharedElementReturnTransition(null);
        baseActivity = (BaseActivity) getActivity();
        if (getArguments() != null){
//            nasaPicture = getArguments().getParcelable(Constants.NASA_PICTURE);
        }
        nasaPictureDetailActivityView = baseActivity.getCompositionRoot().getViewMvcFactory().getNasaPictureDetailActivityView(null);
        baseActivity.getCompositionRoot().getNasaPictureDataContract().getPicturesForNasaGallery(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return nasaPictureDetailActivityView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCompletion(List<NasaPicture> nasaPictureList) {
        mHandler.post(() -> {
            nasaPictureDetailActivityView.showNasaPictureViewPager(baseActivity, nasaPictureList);
        });
    }

    @Override
    public void onFailure(String error) {

    }
}