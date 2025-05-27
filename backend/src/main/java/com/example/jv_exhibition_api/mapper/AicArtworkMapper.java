package com.example.jv_exhibition_api.mapper;

import com.example.jv_exhibition_api.model.Artwork;
import com.fasterxml.jackson.databind.JsonNode;

public class AicArtworkMapper {
    public static Artwork map(JsonNode node) {
        Artwork artwork = new Artwork();

        artwork.setId("aic-" + node.get("id").asText());
        artwork.setSource("AIC");
        artwork.setTitle(node.get("title").asText());
        artwork.setArtist(node.has("artist_title") ? node.get("artist_title").asText() : null);
        artwork.setDate(node.has("date_display") ? node.get("date_display").asText() : null);
        artwork.setMedium(node.has("medium_display") ? node.get("medium_display").asText() : null);

        if (node.has("description") && !node.get("description").isNull()) {
            artwork.setDescription(node.get("description").asText());
        }

        if(node.has("image_id") && !node.get("image_id").isNull()) {
            String imageId = node.get("image_id").asText();
            artwork.setImageUrl("https://www.artic.edu/iiif/2/" + imageId + "/full/400,/0/default.jpg");
        }

        return artwork;
    }
}
