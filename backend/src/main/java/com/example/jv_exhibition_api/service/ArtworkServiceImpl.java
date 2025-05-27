package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkRepository repository;

    @Override
    public List<Artwork> getAllArtworks(int pageNo, int pageSize) {
        return repository.findAll();
    }

    @Override
    public Artwork addArtwork(Artwork artwork) {
        return repository.save(artwork);
    }
}
