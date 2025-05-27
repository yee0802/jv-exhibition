package com.example.jv_exhibition_api.mapper;

import com.example.jv_exhibition_api.model.Artwork;
import com.fasterxml.jackson.databind.JsonNode;

public class CmaArtworkMapper {
    public static Artwork map(JsonNode node) {
        Artwork artwork = new Artwork();

        artwork.setId("cma-" + node.get("id").asText());
        artwork.setSource("CMA");
        artwork.setTitle(node.get("title").asText());

        if(node.has("creators") && node.get("creators").isArray() && !node.get("creators").isEmpty()) {
            artwork.setArtist(node.get("creators").get(0).get("description").asText());
        }

        artwork.setDate(node.has("creation_date") ? node.get("creation_date").asText() : null);
        artwork.setMedium(node.has("technique") ? node.get("technique").asText() : null);
        artwork.setDescription(node.has("description") ? node.get("description").asText() : null);

        if(node.has("images") && node.get("images").has("web") && node.get("images").get("web").has("url")) {
            artwork.setImageUrl(node.get("images").get("web").get("url").asText());
        }

        return artwork;
    }
}

