package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkGetAllResponse;
import com.example.jv_exhibition_api.model.Artwork;

public interface ArtworkService {
    ArtworkGetAllResponse getAllArtworks(int pageNo, int pageSize);
    Artwork addArtwork(Artwork artwork);
}
