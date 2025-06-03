package com.example.jv_exhibition_api.dto;

import com.example.jv_exhibition_api.model.Artwork;
import lombok.Data;

import java.util.List;

@Data
public class ArtworkGetAllResponse {
    private List<Artwork> content;
    private int pageNo;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean last;
}
