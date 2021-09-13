package com.nasa.nasapicturesviewer.view.Detail;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.views.BaseViewMvc;
import com.nasa.nasapicturesviewer.model.NasaPicture;
import com.squareup.picasso.Picasso;

public class NasaPictureDetailView extends BaseViewMvc {

    private final TextView title;
    private final TextView explanation;
    private final TextView copyright;
    private final TextView date;
    private final TextView url;
    private final TextView hdurl;
    private final ImageView image;

    public NasaPictureDetailView(LayoutInflater inflater, ViewGroup parentViewGroup) {
        setRootView(inflater.inflate(R.layout.fragment_nasa_picture_detail, parentViewGroup, false));
        title = findViewById(R.id.title);
        explanation = findViewById(R.id.explanation);
        copyright = findViewById(R.id.copyright);
        date = findViewById(R.id.date);
        url = findViewById(R.id.url);
        hdurl = findViewById(R.id.hdurl);
        image = findViewById(R.id.image);
    }

    public void bindNasaPictureDetailView(NasaPicture nasaPicture) {
        title.setText(nasaPicture.getTitle());
        explanation.setText("Explanation" + "\n" + nasaPicture.getExplanation());
        if (nasaPicture.getCopyright() != null && nasaPicture.getCopyright().length() > 0)
            copyright.setText("Copyright: " + nasaPicture.getCopyright());
        else
            copyright.setVisibility(View.GONE);
        date.setText("Date: " + nasaPicture.getDate());
        url.setText("URL: " + nasaPicture.getUrl());
        hdurl.setText("HD URL: " + nasaPicture.getHdurl());

        image.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(nasaPicture.getHdurl()));
            getContext().startActivity(browserIntent);
        });

        Picasso.get().load(nasaPicture.getHdurl()).placeholder(R.drawable.ic_nasa).fit().into(image);
    }

}
