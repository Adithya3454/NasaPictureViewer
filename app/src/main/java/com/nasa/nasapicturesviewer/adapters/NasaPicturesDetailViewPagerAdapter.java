package com.nasa.nasapicturesviewer.adapters;

import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * View pager that allows to swipe between fragments
 */
public class NasaPicturesDetailViewPagerAdapter extends FragmentStateAdapter {

    private final List<NasaPicture> nasaPicturesList;

    public NasaPicturesDetailViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<NasaPicture> nasaPicturesList) {
        super(fragmentActivity);
        this.nasaPicturesList = nasaPicturesList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return NasaPictureDetailFragment.newInstance(nasaPicturesList.get(position));
    }

    @Override
    public int getItemCount() {
        return nasaPicturesList.size();
    }
}