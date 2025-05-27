package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkGetAllResponse;
import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository repository;

    @Override
    public ArtworkGetAllResponse getAllArtworks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Artwork> artworks = repository.findAll(pageable);
        List<Artwork> artworkList = artworks.getContent();

        ArtworkGetAllResponse getAllResponse = new ArtworkGetAllResponse();
        getAllResponse.setContent(artworkList);
        getAllResponse.setPageNo(artworks.getNumber());
        getAllResponse.setPageSize(artworks.getSize());
        getAllResponse.setTotalPages(artworks.getTotalPages());
        getAllResponse.setTotalElements(artworks.getTotalElements());
        getAllResponse.setLast(artworks.isLast());

        return getAllResponse;
    }

    @Override
    public Artwork addArtwork(Artwork artwork) {
        return repository.save(artwork);
    }
}
