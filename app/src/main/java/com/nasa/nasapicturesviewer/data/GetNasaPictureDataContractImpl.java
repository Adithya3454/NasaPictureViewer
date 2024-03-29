package com.nasa.nasapicturesviewer.data;

import android.content.Context;

import com.nasa.nasapicturesviewer.R;
import com.nasa.nasapicturesviewer.common.Constants;
import com.nasa.nasapicturesviewer.model.NasaPicture;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Class that implements NasaPicturesMainContract.GetNasaPicturesInteractor
 */
public class GetNasaPictureDataContractImpl implements NasaPicturesDataContract {
    private String JSON_FILE_NAME = "data.json";
    private Context context;

    public GetNasaPictureDataContractImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getPicturesForNasaGallery(NasaPicturesDataContract.NasaPicturesLoadingFinishedListener nasaPicturesLoadingFinishedListener) {
        if (nasaPicturesLoadingFinishedListener != null) {
            Runnable parseJSONFileRunnable = () -> {
                List<NasaPicture> nasaPictureList = loadNasaPicturesFromAssets(context);
                nasaPictureList = sortNasaPicturesByMostRecentDate(nasaPictureList);
                if (nasaPictureList.size() > 0)
                    nasaPicturesLoadingFinishedListener.onCompletion(nasaPictureList);
                else
                    nasaPicturesLoadingFinishedListener.onFailure(context.getResources().getString(R.string.no_pictures_available));
            };
            Thread nasaPicturesLoaderThread = new Thread(parseJSONFileRunnable);
            nasaPicturesLoaderThread.start();
        }
    }

    /**
     * Parses the JSONArray and converts it to a List<NasaPictures> and the size of list is 0 in case of an parse error or the if the list is empty.
     *
     * @param context application context of android.
     * @return a List<NasaPicture>
     */
    private List<NasaPicture> loadNasaPicturesFromAssets(Context context) {
        List<NasaPicture> nasaPictureList = new ArrayList<NasaPicture>();

        try {
            JSONArray nasaPicturesJSonArray = new JSONArray(loadJsonFromAssetFile(context, JSON_FILE_NAME));
            String[] nasaPictureObjectKeys = {Constants.copyright_key, Constants.date_key, Constants.explanation_key, Constants.hdurl_key, Constants.media_type_key, Constants.service_version_key, Constants.title_key, Constants.url_key};
            for (int i = 0; i < nasaPicturesJSonArray.length(); i++) {
                JSONObject nasaPictureObject = (JSONObject) nasaPicturesJSonArray.get(i);

                String copyright = null, date = null, explanation = null, hdurl = null, media_type = null, service_version = null, title = null, url = null;

                for (String key : nasaPictureObjectKeys)
                    if (nasaPictureObject.has(key)) {
                        switch (key) {
                            case Constants.copyright_key:
                                copyright = nasaPictureObject.getString(Constants.copyright_key);
                                break;
                            case Constants.date_key:
                                date = nasaPictureObject.getString(Constants.date_key);
                                break;
                            case Constants.explanation_key:
                                explanation = nasaPictureObject.getString(Constants.explanation_key);
                                break;
                            case Constants.hdurl_key:
                                hdurl = nasaPictureObject.getString(Constants.hdurl_key);
                                break;
                            case Constants.media_type_key:
                                media_type = nasaPictureObject.getString(Constants.media_type_key);
                                break;
                            case Constants.service_version_key:
                                service_version = nasaPictureObject.getString(Constants.service_version_key);
                                break;
                            case Constants.title_key:
                                title = nasaPictureObject.getString(Constants.title_key);
                                break;
                            case Constants.url_key:
                                url = nasaPictureObject.getString(Constants.url_key);
                                break;
                        }
                    }

                NasaPicture nasaPicture = new NasaPicture(copyright, date, explanation, hdurl, media_type, service_version, title, url);
                nasaPictureList.add(nasaPicture);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            nasaPictureList.clear();
            return nasaPictureList;
        }

        return nasaPictureList;
    }

    /**
     * Reads the file from assets directory and returns a String with the contents of the file name.
     *
     * @param context application context of android.
     * @param file    name of the file to read.
     * @return String
     */
    private String loadJsonFromAssetFile(Context context, String file) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    /**
     * Sort a list of NasaPicture objects by most recently added
     * @param nasaPictureList a list of pictures to be sorted
     * @return a ist of sorted pictures
     */
    public List<NasaPicture> sortNasaPicturesByMostRecentDate(List<NasaPicture> nasaPictureList){
        final DateFormat nasaPictureDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Collections.sort(nasaPictureList, new Comparator<NasaPicture>() {
            @Override
            public int compare(NasaPicture o1, NasaPicture o2) {
                try {
                    return nasaPictureDateFormat.parse(o2.getDate()).compareTo(nasaPictureDateFormat.parse(o1.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        return nasaPictureList;
    }
}
