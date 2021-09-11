package com.nasa.nasapicturesviewer.view.Detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.BaseActivity;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import static com.nasa.nasapicturesviewer.common.Constants.NASA_PICTURE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NasaPictureDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
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
     * this fragment using the provided parameters.
     *
     * @param nasaPicture Parameter 1.
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
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
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
        ImageView imageView = nasaPictureDetailView.getRootView().findViewById(R.id.image);
        TextView textView = nasaPictureDetailView.getRootView().findViewById(R.id.explanation);

        Log.i(LOG_TAG, "tranistion name: "+nasaPicture.getUrl());
        ViewCompat.setTransitionName(imageView, nasaPicture.getUrl());

        Picasso.get()
                .load(nasaPicture.getUrl())
                .noFade()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i("download", "image downloaded");
                        startPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i("download", "download failed");
                        startPostponedEnterTransition();
                    }
                });
    }


}