package com.example.jv_exhibition.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

public class Artwork extends BaseObservable implements Parcelable {
    private String id;
    private String title;
    private String description;
    private String artist;
    private String imageUrl;
    private String source;
    private String date;
    private String medium;

    public Artwork() {
    }

    public Artwork(String id, String title, String description, String artist, String imageUrl, String date, String source, String medium) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.imageUrl = imageUrl;
        this.date = date;
        this.source = source;
        this.medium = medium;
    }

    protected Artwork(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        artist = in.readString();
        imageUrl = in.readString();
        source = in.readString();
        date = in.readString();
        medium = in.readString();
    }

    public static final Creator<Artwork> CREATOR = new Creator<Artwork>() {
        @Override
        public Artwork createFromParcel(Parcel in) {
            return new Artwork(in);
        }

        @Override
        public Artwork[] newArray(int size) {
            return new Artwork[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(artist);
        parcel.writeString(imageUrl);
        parcel.writeString(source);
        parcel.writeString(date);
        parcel.writeString(medium);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
