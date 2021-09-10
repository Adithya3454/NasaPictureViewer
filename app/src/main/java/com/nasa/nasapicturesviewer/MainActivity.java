package com.nasa.nasapicturesviewer;

import android.os.Bundle;

import com.nasa.nasapicturesviewer.view.NasaPictureActivityView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private NasaPictureActivityView nasaPictureActivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nasaPictureActivityView = new NasaPictureActivityView(getLayoutInflater(), null);
        setContentView(nasaPictureActivityView.getRootView());
        super.onCreate(savedInstanceState);
    }
}