package com.nasa.nasapicturesviewer.common.dependencyinjection;

import android.content.Context;
import android.view.LayoutInflater;

import com.nasa.nasapicturesviewer.common.ScreensNavigator;
import com.nasa.nasapicturesviewer.common.views.ViewMvcFactory;
import com.nasa.nasapicturesviewer.data.GetNasaPictureDataContractImpl;
import com.nasa.nasapicturesviewer.view.Detail.NasaPictureDetailsViewController;
import com.nasa.nasapicturesviewer.view.List.NasaPictureListController;

import androidx.fragment.app.FragmentActivity;

/**
 *  Class for getting instances of other classes
 */
public class ControllerCompositionRoot {

    private final ActivityCompositionRoot mActivityCompositionRoot;

    public ControllerCompositionRoot(ActivityCompositionRoot activityCompositionRoot) {
        mActivityCompositionRoot = activityCompositionRoot;
    }

    private FragmentActivity getActivity() {
        return mActivityCompositionRoot.getActivity();
    }

    private Context getContext() {
        return getActivity();
    }

    private LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(getLayoutInflater());
    }

    public GetNasaPictureDataContractImpl getNasaPictureDataContract(){
        return mActivityCompositionRoot.getNasaPictureDataContract(getContext());
    }

    public NasaPictureListController getNasaPictureListController(){
        return new NasaPictureListController(getNasaPictureDataContract(), getScreensNavigator());
    }

    public ScreensNavigator getScreensNavigator(){
        return new ScreensNavigator(getContext());
    }

    public NasaPictureDetailsViewController getNasaPictureDetailsViewController() {
        return new NasaPictureDetailsViewController(getActivity());
    }
}
