package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkDTO;
import com.example.jv_exhibition_api.dto.ExhibitionDTO;
import com.example.jv_exhibition_api.model.Exhibition;

public interface ExhibitionService {
    Exhibition getExhibitionById(Long id);
    Exhibition createExhibition(ExhibitionDTO exhibitionDTO);
    Exhibition addArtworkToExhibition(Long exhibitionId, ArtworkDTO artworkDTO);
    Exhibition removeArtworkFromExhibition(Long exhibitionId, ArtworkDTO artworkDTO);
}
