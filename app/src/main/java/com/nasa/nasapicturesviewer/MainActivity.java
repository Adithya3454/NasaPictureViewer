package com.nasa.nasapicturesviewer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.data.NasaPicturesDataContract;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.NasaPictureActivityView;

import java.util.List;

public class MainActivity extends BaseActivity implements NasaPicturesDataContract.NasaPicturesLoadingFinishedListener{

    private NasaPictureActivityView nasaPictureActivityView;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nasaPictureActivityView = new NasaPictureActivityView(getLayoutInflater(), null, getCompositionRoot().getViewMvcFactory());
        setContentView(nasaPictureActivityView.getRootView());
        super.onCreate(savedInstanceState);
        getCompositionRoot().getNasaPictureDataContract().getPicturesForNasaGallery(this);
    }

    @Override
    public void onCompletion(List<NasaPicture> nasaPictureList) {
        mHandler.post(() -> nasaPictureActivityView.setGridRecyclerView(nasaPictureList));
    }

    @Override
    public void onFailure(String error) {

    }
}