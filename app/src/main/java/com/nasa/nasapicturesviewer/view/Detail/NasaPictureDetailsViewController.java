package com.nasa.nasapicturesviewer.view.Detail;

import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

import androidx.fragment.app.FragmentActivity;

public class NasaPictureDetailsViewController {

    private NasaPictureDetailActivityView nasaPictureDetailActivityView;
    private final FragmentActivity fragmentActivity;
    private List<NasaPicture> nasaPicturesList;
    private int selectedPictureIndex;

    public NasaPictureDetailsViewController(FragmentActivity activity) {
        this.fragmentActivity = activity;
    }

    public void setNasaPictureListAndSelectedIndex(List<NasaPicture> nasaPicturesList, int selectedPictureIndex) {
        this.nasaPicturesList = nasaPicturesList;
        this.selectedPictureIndex = selectedPictureIndex;
    }

    public void onStart(){
        nasaPictureDetailActivityView.showNasaPictureViewPager(fragmentActivity, nasaPicturesList, selectedPictureIndex);
    }

    public void onStop(){
    }

    public void bindView(NasaPictureDetailActivityView nasaPictureDetailActivityView) {
        this.nasaPictureDetailActivityView = nasaPictureDetailActivityView;
    }


}
