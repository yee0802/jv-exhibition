package com.example.jv_exhibition_api.controller;

import com.example.jv_exhibition_api.dto.ArtworkDTO;
import com.example.jv_exhibition_api.dto.ExhibitionDTO;
import com.example.jv_exhibition_api.model.Exhibition;
import com.example.jv_exhibition_api.service.ExhibitionServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ExhibitionControllerTest {
    @Mock
    private ExhibitionServiceImpl mockService;

    @InjectMocks
    private ExhibitionController exhibitionController;

    @Autowired
    private MockMvc mockMvcController;

    private Exhibition exhibition;
    private ExhibitionDTO exhibitionDTO;
    private ArtworkDTO artworkDTO;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(exhibitionController).build();

        exhibitionDTO = new ExhibitionDTO();
        exhibitionDTO.setName("exhibition1");

        exhibition = new Exhibition();
        exhibition.setId(1L);
        exhibition.setName("exhibition1");

        artworkDTO = new ArtworkDTO();
        artworkDTO.setId("cma-000");
    }

    @Test
    @DisplayName("GET /exhibitions/{id} - should return exhibition by id")
    void testGetExhibitionById() throws Exception {
        when(mockService.getExhibitionById(1L)).thenReturn(exhibition);

        mockMvcController.perform(get("/exhibitions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("exhibition1"));
    }

    @Test
    @DisplayName("POST /exhibitions - should create exhibition")
    void testCreateExhibition() throws Exception {
        when(mockService.createExhibition(any())).thenReturn(exhibition);

        mockMvcController.perform(post("/exhibitions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(exhibitionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("exhibition1"));
    }

    @Test
    @DisplayName("POST /exhibitions/{id}/add - should add artwork to specified exhibition")
    void testAddArtworkToExhibition() throws Exception {
        when(mockService.addArtworkToExhibition(eq(1L), any())).thenReturn(exhibition);

        mockMvcController.perform(post("/exhibitions/1/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(artworkDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /exhibitions/{id}/remove - should remove artwork from specified exhibition")
    void testRemoveArtworkFromExhibition() throws Exception {
        when(mockService.removeArtworkFromExhibition(eq(1L), any())).thenReturn(exhibition);

        mockMvcController.perform(post("/exhibitions/1/remove")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJSON(artworkDTO)))
                .andExpect(status().isOk());
    }

    private String toJSON(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
