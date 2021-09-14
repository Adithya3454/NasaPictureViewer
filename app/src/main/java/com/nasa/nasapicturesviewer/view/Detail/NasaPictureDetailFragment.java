package com.nasa.nasapicturesviewer.view.Detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.nasa.nasapicturesviewer.common.Constants.NASA_PICTURE;

/**
 * Fragment that displays all the details of a picture
 */
public class NasaPictureDetailFragment extends Fragment {

    private NasaPicture nasaPicture;
    private NasaPictureDetailView nasaPictureDetailView;
    private static final String LOG_TAG = NasaPictureDetailFragment.class.getSimpleName();


    public NasaPictureDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @param nasaPicture details of the picture to be displayed.
     * @return A new instance of fragment NasaPictureDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NasaPictureDetailFragment newInstance(NasaPicture nasaPicture) {
        NasaPictureDetailFragment fragment = new NasaPictureDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(NASA_PICTURE, nasaPicture);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nasaPicture = getArguments().getParcelable(NASA_PICTURE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        BaseActivity activity = (BaseActivity) getActivity();
        nasaPictureDetailView = activity.getCompositionRoot().getViewMvcFactory().getNasaPictureDetailView(container);
        return nasaPictureDetailView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nasaPictureDetailView.bindNasaPictureDetailView(nasaPicture);
    }


}