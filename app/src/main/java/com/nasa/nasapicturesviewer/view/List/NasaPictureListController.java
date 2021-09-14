package com.nasa.nasapicturesviewer.view.List;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.nasa.nasapicturesviewer.common.ScreensNavigator;
import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;
import com.nasa.nasapicturesviewer.data.NasaPicturesDataContract;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

/**
 * Controller for Activity (NasaPictureActivity) that displays all pictures
 */
public class NasaPictureListController implements
        NasaPicturesDataContract.NasaPicturesLoadingFinishedListener,
        NasaPictureListViewMvc.NasaPictureCLickListener {

    private final GetNasaPictureDataContractImpl nasaPictureDataContract;
    private NasaPictureListActivityView nasaPictureListActivityView;
    private final ScreensNavigator screensNavigator;
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private List<NasaPicture> nasaPicturesList;

    public NasaPictureListController(GetNasaPictureDataContractImpl nasaPictureDataContract, ScreensNavigator screensNavigator) {
        this.nasaPictureDataContract = nasaPictureDataContract;
        this.screensNavigator = screensNavigator;
    }

    public void onStart(){
        nasaPictureDataContract.getPicturesForNasaGallery(this);
    }

    public void onStop(){
    }

    @Override
    public void onCompletion(List<NasaPicture> nasaPictureList) {
        mainThreadHandler.post(() -> {
            this.nasaPicturesList = nasaPictureList;
            nasaPictureListActivityView.setGridRecyclerView(nasaPictureList);});
    }

    @Override
    public void onFailure(String error) {

    }

    public void bindView(NasaPictureListActivityView nasaPictureActivityView) {
        this.nasaPictureListActivityView = nasaPictureActivityView;
    }

    @Override
    public void onPictureClicked(NasaPicture nasaPicture, View clickedNasaPictureView) {
        screensNavigator.GoToNasaPictureDetailActivity(nasaPicturesList.indexOf(nasaPicture), nasaPicturesList);
    }
}
