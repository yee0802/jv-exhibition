package com.example.jv_exhibition_api.controller;

import com.example.jv_exhibition_api.dto.ArtworkGetAllResponse;
import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.service.ArtworkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class ArtworkControllerTest {
    @Mock
    private ArtworkServiceImpl mockService;

    @InjectMocks
    private ArtworkController artworkController;

    @Autowired
    private MockMvc mockMvcController;

    @BeforeEach()
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(artworkController).build();
    }

    @Test
    @DisplayName("GET /artworks: should return paginated response")
    public void testGetAllArtworks_ReturnsOkWithJson() throws Exception {
        Artwork artwork1 = new Artwork();
        artwork1.setTitle("artwork1");

        Artwork artwork2 = new Artwork();
        artwork2.setTitle("artwork2");

        ArtworkGetAllResponse mockResponse = new ArtworkGetAllResponse();
        mockResponse.setContent(Arrays.asList(artwork1, artwork2));
        mockResponse.setPageNo(0);
        mockResponse.setPageSize(2);
        mockResponse.setTotalPages(5);
        mockResponse.setTotalElements(10L);
        mockResponse.setLast(false);

        when(mockService.getAllArtworks(anyInt(), anyInt()))
                .thenReturn(mockResponse);

        mockMvcController.perform(get("/artworks")
                        .param("pageNo", "0")
                        .param("pageSize", "2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].title").value("artwork1"))
                .andExpect(jsonPath("$.pageNo").value(0))
                .andExpect(jsonPath("$.pageSize").value(2))
                .andExpect(jsonPath("$.totalPages").value(5))
                .andExpect(jsonPath("$.last").value(false));
    }
}
