package com.example.jv_exhibition_api.controller;

import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping
    public ResponseEntity<List<Artwork>> getAllArtworks() {
        List<Artwork> artworks = artworkService.getAllArtworks();

        return new ResponseEntity<>(artworks, HttpStatus.OK);
    }
}
