package com.nasa.nasapicturesviewer.view.Detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.adapters.NasaPicturesDetailViewPagerAdapter;
import com.nasa.nasapicturesviewer.common.views.BaseViewMvc;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class NasaPictureDetailActivityView extends BaseViewMvc {

    private final ViewPager2 nasaPictureViewPager;
    private final static String LOG_TAG = NasaPictureDetailActivityView.class.getSimpleName();

    public NasaPictureDetailActivityView(LayoutInflater inflater, ViewGroup parentViewGroup) {
        setRootView(inflater.inflate(R.layout.activity_nasa_picture_detail, parentViewGroup, false));
        nasaPictureViewPager = findViewById(R.id.nasa_pictures_viewpager);
    }

    public void showNasaPictureViewPager(FragmentActivity fragmentActivity,  List<NasaPicture> nasaPictureList){
//        Log.i(LOG_TAG, "current nasa picture: "+currentNasaPicture.toString());
//        Log.i(LOG_TAG, "index of selected picture: "+indexOfCurrentNasaPicture);
        nasaPictureViewPager.setAdapter(new NasaPicturesDetailViewPagerAdapter(fragmentActivity, nasaPictureList));
        nasaPictureViewPager.setCurrentItem(0);
    }

}