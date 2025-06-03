package com.example.jv_exhibition_api.mapper;

import com.example.jv_exhibition_api.model.Artwork;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtworkMapperTest {
    @Test
    @DisplayName("AicArtworkMapper.map: should correctly map JSON to artwork")
    public void testAicArtworkMapper() throws Exception {
        String json = """
                {
                   "id": 161,
                   "title": "Skyphos (Drinking Cup)",
                   "date_display": "410-400 BCE",
                   "description": "During the course of the 5th and 4th centuries BCE, black vessels (commonly called black-glaze vessels) were made with increasing frequency in both Greece and South Italy. Many of them replicate the shape of metal vessels. Others have detailing that is molded or incised. While the quality of these vessels varies greatly, all would have been less expensive than vessels decorated in other contemporary techniques, for example, in red-figure.",
                   "medium_display": "terracotta, black-glaze with impressed decoration",
                   "artist_title": "Ancient Greek",
                   "classification_title": "drinking vessel",
                   "image_id": "1bc27523-6b27-d9b1-4ea0-ec436d6fd95e"
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        Artwork artwork = AicArtworkMapper.map(node);

        assertEquals("aic-161", artwork.getId());
        assertEquals("AIC", artwork.getSource());
        assertEquals("Skyphos (Drinking Cup)", artwork.getTitle());
        assertEquals("Ancient Greek", artwork.getArtist());
        assertEquals("410-400 BCE", artwork.getDate());
        assertEquals("terracotta, black-glaze with impressed decoration", artwork.getMedium());
        assertEquals("During the course of the 5th and 4th centuries BCE, black vessels (commonly called black-glaze vessels) were made with increasing frequency in both Greece and South Italy. Many of them replicate the shape of metal vessels. Others have detailing that is molded or incised. While the quality of these vessels varies greatly, all would have been less expensive than vessels decorated in other contemporary techniques, for example, in red-figure.", artwork.getDescription());
        assertEquals("https://www.artic.edu/iiif/2/1bc27523-6b27-d9b1-4ea0-ec436d6fd95e/full/400,/0/default.jpg", artwork.getImageUrl());
    }

    @Test
    @DisplayName("CmaArtworkMapper.map: should correctly map JSON to artwork")
    public void testCmaArtworkMapper() throws Exception {
        String json = """
        {
            "id": 151904,
            "accession_number": "1927.1984",
            "title": "The Biglin Brothers Turning the Stake",
            "creation_date": "1873",
            "technique": "oil on canvas",
            "images": {
                "annotation": null,
                "web": {
                    "url": "https://openaccess-cdn.clevelandart.org/1927.1984/1927.1984_web.jpg",
                    "width": "1263",
                    "height": "835",
                    "filesize": "780656",
                    "filename": "1927.1984_web.jpg"
                },
                "print": {
                    "url": "https://openaccess-cdn.clevelandart.org/1927.1984/1927.1984_print.jpg",
                    "width": "3400",
                    "height": "2247",
                    "filesize": "5271592",
                    "filename": "1927.1984_print.jpg"
                },
                "full": {
                    "url": "https://openaccess-cdn.clevelandart.org/1927.1984/1927.1984_full.tif",
                    "width": "7164",
                    "height": "4735",
                    "filesize": "101799424",
                    "filename": "1927.1984_full.tif"
                }
            },
            "creators": [
                {
                    "id": 4037,
                    "description": "Thomas Eakins (American, 1844–1916)",
                    "extent": null,
                    "qualifier": null,
                    "role": "artist",
                    "biography": null,
                    "name_in_original_language": null,
                    "birth_year": "1844",
                    "death_year": "1916"
                }
            ]
        }
        """;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);

        Artwork artwork = CmaArtworkMapper.map(node);

        assertEquals("cma-151904", artwork.getId());
        assertEquals("CMA", artwork.getSource());
        assertEquals("The Biglin Brothers Turning the Stake", artwork.getTitle());
        assertEquals("Thomas Eakins (American, 1844–1916)", artwork.getArtist());
        assertEquals("1873", artwork.getDate());
        assertEquals("oil on canvas", artwork.getMedium());
        assertEquals("https://openaccess-cdn.clevelandart.org/1927.1984/1927.1984_web.jpg", artwork.getImageUrl());
    }
}
