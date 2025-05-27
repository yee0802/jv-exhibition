package com.example.jv_exhibition_api.model;

import jakarta.persistence.*;

@Table(name = "artworks")
@Entity
public class Artwork {
    @Id
    private String id;

    @Column(columnDefinition="text")
    private String title;

    @Column(columnDefinition="text")
    private String description;

    @Column
    private String artist;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private String source;

    @Column
    private String date;

    @Column(columnDefinition="text")
    private String medium;

    public Artwork() {
    }

    public Artwork(String id, String medium, String date, String source, String imageUrl, String artist, String description, String title) {
        this.id = id;
        this.medium = medium;
        this.date = date;
        this.source = source;
        this.imageUrl = imageUrl;
        this.artist = artist;
        this.description = description;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
