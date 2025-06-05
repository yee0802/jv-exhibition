package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkDTO;
import com.example.jv_exhibition_api.dto.ExhibitionDTO;
import com.example.jv_exhibition_api.exception.ItemNotFoundException;
import com.example.jv_exhibition_api.exception.MissingFieldException;
import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.model.Exhibition;
import com.example.jv_exhibition_api.repository.ArtworkRepository;
import com.example.jv_exhibition_api.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Override
    public List<Exhibition> getAllExhibitions() {
        return exhibitionRepository.findAll();
    }

    @Override
    public Exhibition getExhibitionById(Long id) {
        return exhibitionRepository.findById(id)
                 .orElseThrow(() -> new ItemNotFoundException(String.format("Exhibition with id '%s' could not be found", id)));
    }

    @Override
    public Exhibition createExhibition(ExhibitionDTO exhibitionDTO) {
        if (exhibitionDTO.getName() == null) {
            throw new MissingFieldException("Missing field(s) in request body");
        }

        String name = exhibitionDTO.getName();

        Exhibition newExhibition = new Exhibition();
        newExhibition.setName(name);

        return exhibitionRepository.save(newExhibition);
    }

    @Override
    public Exhibition removeArtworkFromExhibition(Long exhibitionId, ArtworkDTO artworkDTO) {
        if (artworkDTO.getId() == null) {
            throw new MissingFieldException("Missing field(s) in request body");
        }

        String artworkId = artworkDTO.getId();

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Exhibition with id '%s' could not be found", exhibitionId)));

        exhibition.getArtworks().removeIf(artwork -> artwork.getId().equals(artworkId));

        return exhibitionRepository.save(exhibition);
    }


    @Override
    public Exhibition addArtworkToExhibition(Long exhibitionId, ArtworkDTO artworkDTO) {
        if (artworkDTO.getId() == null) {
            throw new MissingFieldException("Missing field(s) in request body");
        }

        String artworkId = artworkDTO.getId();

        Exhibition exhibition = exhibitionRepository.findById(exhibitionId)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Exhibition with id '%s' could not be found", exhibitionId)));

        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Artwork with id '%s' could not be found", artworkId)));

        exhibition.getArtworks().add(artwork);

        return exhibitionRepository.save(exhibition);
    }
}
