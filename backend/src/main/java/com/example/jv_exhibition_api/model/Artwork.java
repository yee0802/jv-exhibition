package com.example.jv_exhibition_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
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
}
