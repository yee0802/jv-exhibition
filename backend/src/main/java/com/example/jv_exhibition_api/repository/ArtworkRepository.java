package com.example.jv_exhibition_api.repository;

import com.example.jv_exhibition_api.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {}
