package com.nasa.nasapicturesviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class to hold a Nasa picture and it's details.
 */
public class NasaPicture implements Parcelable {

    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    /**
     * Create a Nasa picture
     * @param copyright copyright of the picture
     * @param date date when the picture was taken
     * @param explanation description about the image and its contents
     * @param hdurl an url to high definition version of the image
     * @param media_type the type of media
     * @param service_version version of the image
     * @param title title of the image
     * @param url url to the image
     */
    public NasaPicture(String copyright, String date, String explanation, String hdurl, String media_type, String service_version, String title, String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public void setService_version(String service_version) {
        this.service_version = service_version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NasaPicture{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media_type='" + media_type + '\'' +
                ", service_version='" + service_version + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(copyright);
        dest.writeString(date);
        dest.writeString(explanation);
        dest.writeString(hdurl);
        dest.writeString(media_type);
        dest.writeString(service_version);
        dest.writeString(title);
        dest.writeString(url);
    }

    protected NasaPicture(Parcel in) {
        copyright = in.readString();
        date = in.readString();
        explanation = in.readString();
        hdurl = in.readString();
        media_type = in.readString();
        service_version = in.readString();
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<NasaPicture> CREATOR = new Creator<NasaPicture>() {
        @Override
        public NasaPicture createFromParcel(Parcel in) {
            return new NasaPicture(in);
        }

        @Override
        public NasaPicture[] newArray(int size) {
            return new NasaPicture[size];
        }
    };

}
