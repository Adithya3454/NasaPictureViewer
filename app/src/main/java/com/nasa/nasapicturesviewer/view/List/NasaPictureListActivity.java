package com.nasa.nasapicturesviewer.view.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.model.NasaPicture;

public class NasaPictureListActivity extends BaseActivity implements  NasaPictureListViewMvc.NasaPictureCLickListener {

    private static final String LOG_TAG = NasaPictureListActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, NasaPictureListFragment.newInstance())
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onPictureClicked(NasaPicture nasaPicture, View clickedNasaPictureView) {
        Log.i(LOG_TAG, "--- on picture clicked ---"+nasaPicture.toString());
        /*Intent nasaDetailPictureActivity = new Intent(this, NasaPictureDetailParentFragment.class);
        nasaDetailPictureActivity.putExtra(Constants.SELECTED_PICTURE, nasaPicture);

        nasaDetailPictureActivity.putExtra(Constants.IMAGE_TRANSITION_NAME, ViewCompat.getTransitionName(clickedNasaPictureView));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                clickedNasaPictureView,
                ViewCompat.getTransitionName(clickedNasaPictureView));


         */




    }
}
