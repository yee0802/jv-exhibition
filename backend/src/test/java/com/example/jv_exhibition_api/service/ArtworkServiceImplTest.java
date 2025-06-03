package com.example.jv_exhibition_api.service;

import com.example.jv_exhibition_api.dto.ArtworkGetAllResponse;
import com.example.jv_exhibition_api.model.Artwork;
import com.example.jv_exhibition_api.repository.ArtworkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ArtworkServiceImplTest {

    @Mock
    ArtworkRepository mockRepository;

    @InjectMocks
    ArtworkServiceImpl mockService;

    @Test
    @DisplayName("getAllArtworks: should return paginated response")
    public void testGetAllArtworks_ReturnsPaginatedResponse() {
        Artwork artwork1 = new Artwork();
        artwork1.setTitle("artwork1");

        Artwork artwork2 = new Artwork();
        artwork2.setTitle("artwork2");

        List<Artwork> artworks = Arrays.asList(artwork1, artwork2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Artwork> page = new PageImpl<>(artworks, pageable, 5);

        when(mockRepository.findAll(pageable)).thenReturn(page);

        ArtworkGetAllResponse result = mockService.getAllArtworks(0, 2);

        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(0, result.getPageNo());
        assertEquals(2, result.getPageSize());
        assertEquals(3, result.getTotalPages());
        assertEquals(5, result.getTotalElements());
        assertFalse(result.isLast());

        verify(mockRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("addArtwork: should save and return artwork")
    public void testAddArtwork_SavesAndReturnsArtwork() {
        Artwork artwork = new Artwork();
        artwork.setTitle("artwork1");

        when(mockRepository.save(artwork)).thenReturn(artwork);

        Artwork savedArtwork = mockService.addArtwork(artwork);

        assertNotNull(savedArtwork);
        assertEquals("artwork1", savedArtwork.getTitle());

        verify(mockRepository, times(1)).save(artwork);
    }
}
