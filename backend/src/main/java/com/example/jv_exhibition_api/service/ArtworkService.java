package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.model.Artwork;

import java.util.List;

public interface ArtworkService {
    List<Artwork> getAllArtworks();
    Artwork addArtwork(Artwork artwork);
}
