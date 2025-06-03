package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkDTO;
import com.example.jv_exhibition_api.dto.ExhibitionDTO;
import com.example.jv_exhibition_api.exception.ItemNotFoundException;
import com.example.jv_exhibition_api.exception.MissingFieldException;
import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.model.Exhibition;
import com.example.jv_exhibition_api.repository.ArtworkRepository;
import com.example.jv_exhibition_api.repository.ExhibitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ExhibitionServiceImplTest {
    @Mock
    ExhibitionRepository mockExhibitionRepository;

    @Mock
    ArtworkRepository mockArtworkRepository;

    @InjectMocks
    ExhibitionServiceImpl mockService;

    private ExhibitionDTO exhibitionDTO;
    private Exhibition exhibition;
    private ArtworkDTO artworkDTO;
    private Artwork artwork;

    @BeforeEach
    public void setup() {
        artworkDTO = new ArtworkDTO();
        artworkDTO.setId("cma-000");

        artwork = new Artwork();
        artwork.setId("cma-000");
        artwork.setTitle("title");
        artwork.setDescription("desc");

        exhibitionDTO = new ExhibitionDTO();
        exhibitionDTO.setName("exhibition1");

        exhibition = new Exhibition();
        exhibition.setId(1L);
        exhibition.setName("exhibition1");
        exhibition.setArtworks(new ArrayList<>());
        exhibition.getArtworks().add(artwork);
    }

    @Test
    @DisplayName("getExhibitionById: should return exhibition by id")
    public void testGetExhibitionById() {
        when(mockExhibitionRepository.findById(1L)).thenReturn(Optional.of(exhibition));

        Exhibition result = mockService.getExhibitionById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("exhibition1", result.getName());

        verify(mockExhibitionRepository).findById(1L);
    }

    @Test
    @DisplayName("getExhibitionById: should throw ItemNotFoundException")
    public void testGetExhibitionById_ThrowsException() {
        assertThrows(ItemNotFoundException.class, () -> mockService.getExhibitionById(2L));

        verify(mockExhibitionRepository).findById(2L);
    }

    @Test
    @DisplayName("createExhibition: should create new exhibition")
    public void testCreateExhibition() {
        when(mockExhibitionRepository.save(any())).thenReturn(exhibition);

        Exhibition result = mockService.createExhibition(exhibitionDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("exhibition1", result.getName());

        verify(mockExhibitionRepository).save(any(Exhibition.class));
    }

    @Test
    @DisplayName("createExhibition: should throw MissingFieldException")
    public void testCreateExhibition_ThrowsException() {
        ExhibitionDTO invalidExhibitionDto = new ExhibitionDTO();

        assertThrows(MissingFieldException.class, () -> mockService.createExhibition(invalidExhibitionDto));

        verify(mockExhibitionRepository, never()).save(any(Exhibition.class));
    }

    @Test
    @DisplayName("removeArtworkFromExhibition: should remove artwork from exhibition")
    public void testRemoveArtworkFromExhibition() {
        when(mockExhibitionRepository.findById(1L)).thenReturn(Optional.of(exhibition));
        when(mockExhibitionRepository.save(any())).thenReturn(exhibition);

        Exhibition result = mockService.removeArtworkFromExhibition(1L, artworkDTO);

        assertNotNull(result);
        assertTrue(result.getArtworks().isEmpty());

        verify(mockExhibitionRepository).findById(1L);
        verify(mockExhibitionRepository).save(any(Exhibition.class));
    }

    @Test
    @DisplayName("removeArtworkFromExhibition: should throw MissingFieldException")
    public void testRemoveArtworkFromExhibition_ThrowsException() {
        ArtworkDTO invalidArtworkDTO = new ArtworkDTO();

        assertThrows(MissingFieldException.class, () -> mockService.removeArtworkFromExhibition(1L, invalidArtworkDTO));

        verify(mockExhibitionRepository, never()).save(any(Exhibition.class));
    }

    @Test
    @DisplayName("addArtworkFromExhibition: should add artwork to exhibition")
    public void testAddArtworkToExhibition() {
        when(mockExhibitionRepository.findById(1L)).thenReturn(Optional.of(new Exhibition()));
        when(mockArtworkRepository.findById("cma-000")).thenReturn(Optional.of(artwork));
        when(mockExhibitionRepository.save(any())).thenReturn(exhibition);

        Exhibition result = mockService.addArtworkToExhibition(1L, artworkDTO);

        assertNotNull(result);
        assertEquals(1, result.getArtworks().size());
        assertTrue(result.getArtworks().contains(artwork));

        verify(mockExhibitionRepository).findById(1L);
        verify(mockArtworkRepository).findById("cma-000");
        verify(mockExhibitionRepository).save(any(Exhibition.class));
    }

    @Test
    @DisplayName("addArtworkFromExhibition: should throw MissingFieldException")
    public void testAddArtworkToExhibition_ThrowsException() {
        ArtworkDTO invalidArtworkDTO = new ArtworkDTO();

        assertThrows(MissingFieldException.class, () -> mockService.addArtworkToExhibition(1L, invalidArtworkDTO));

        verify(mockExhibitionRepository, never()).save(any(Exhibition.class));
    }
}
