package com.nasa.nasapicturesviewer.view.Detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;

import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.common.Constants;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class NasaPictureDetailActivity extends BaseActivity {

    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private NasaPictureDetailsViewController nasaPictureDetailsViewController;

    public static void start(Context context, int selectedPictureIndex, List<NasaPicture> nasaPictureList) {
        Intent intent = new Intent(context, NasaPictureDetailActivity.class);
        intent.putExtra(Constants.SELECTED_PICTURE_INDEX, selectedPictureIndex);
        intent.putParcelableArrayListExtra(Constants.SELECTED_PICTURE, (ArrayList<? extends Parcelable>) nasaPictureList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nasaPictureDetailsViewController = getCompositionRoot().getNasaPictureDetailsViewController();
        NasaPictureDetailActivityView nasaPictureDetailActivityView = getCompositionRoot().getViewMvcFactory().getNasaPictureDetailActivityView(null);
        nasaPictureDetailsViewController.bindView(nasaPictureDetailActivityView);
        nasaPictureDetailsViewController.setNasaPictureListAndSelectedIndex(getNasaPicturesList(), getSelectedPictureIndex());
        setContentView(nasaPictureDetailActivityView.getRootView());
    }

    private List<NasaPicture> getNasaPicturesList(){
        return getIntent().getParcelableArrayListExtra(Constants.SELECTED_PICTURE);
    }

    private int getSelectedPictureIndex(){
        return getIntent().getIntExtra(Constants.SELECTED_PICTURE_INDEX, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        nasaPictureDetailsViewController.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        nasaPictureDetailsViewController.onStop();
    }
}