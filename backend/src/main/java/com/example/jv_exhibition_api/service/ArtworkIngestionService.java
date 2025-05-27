package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.mapper.CmaArtworkMapper;
import com.example.jv_exhibition_api.model.Artwork;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.example.jv_exhibition_api.mapper.AicArtworkMapper;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArtworkIngestionService {

    @Autowired
    private ArtworkService artworkService;

    private final WebClient webClient = WebClient.create();
    private final ObjectMapper mapper = new ObjectMapper();

    public void ingestFromAllSources() {
        ingestCmaArtworks();
        ingestAicArtworks();
    }

    private void ingestAicArtworks() {
        String url = "https://api.artic.edu/api/v1/artworks?page=1&limit=25&fields=id,title,artist_title,date_display,medium_display,description,image_id";

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode root = mapper.readTree(response);
            JsonNode data = root.path("data");

            for (JsonNode artworkNode : data) {
                Artwork artwork = AicArtworkMapper.map(artworkNode);
                artworkService.addArtwork(artwork);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ingestCmaArtworks() {
        String url = "https://openaccess-api.clevelandart.org/api/artworks/?page=1&limit=25&has_image=1&fields=id,title,images,creators,creation_date,technique";

        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        try {
            JsonNode root = mapper.readTree(response);
            JsonNode data = root.path("data");

            for (JsonNode artworkNode : data) {
                Artwork artwork = CmaArtworkMapper.map(artworkNode);
                artworkService.addArtwork(artwork);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
