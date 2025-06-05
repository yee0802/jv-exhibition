package com.example.jv_exhibition_api.controller;

import com.example.jv_exhibition_api.dto.ArtworkDTO;
import com.example.jv_exhibition_api.dto.ExhibitionDTO;
import com.example.jv_exhibition_api.model.Exhibition;
import com.example.jv_exhibition_api.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exhibitions")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping
    public ResponseEntity<List<Exhibition>> getAllExhibitions() {
        return new ResponseEntity<>(exhibitionService.getAllExhibitions(), HttpStatus.OK);
    }

    @GetMapping("/{exhibitionId}")
    public ResponseEntity<Exhibition> getExhibitionById(@PathVariable Long exhibitionId) {
        return new ResponseEntity<>(exhibitionService.getExhibitionById(exhibitionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exhibition> createExhibition(@RequestBody ExhibitionDTO exhibitionDTO) {
        return new ResponseEntity<>(exhibitionService.createExhibition(exhibitionDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{exhibitionId}/remove")
    public ResponseEntity<Exhibition> removeArtworkFromExhibition(
            @PathVariable Long exhibitionId, @RequestBody ArtworkDTO artworkDTO) {
        return new ResponseEntity<>(exhibitionService.removeArtworkFromExhibition(exhibitionId, artworkDTO), HttpStatus.OK);
    }

    @PostMapping("/{exhibitionId}/add")
    public ResponseEntity<Exhibition> addArtworkToExhibition(
            @PathVariable Long exhibitionId, @RequestBody ArtworkDTO artworkDTO) {
        return new ResponseEntity<>(exhibitionService.addArtworkToExhibition(exhibitionId, artworkDTO), HttpStatus.OK);
    }
}

