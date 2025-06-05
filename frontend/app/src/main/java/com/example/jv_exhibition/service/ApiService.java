package com.example.jv_exhibition.service;

import com.example.jv_exhibition.model.ArtworkResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("artworks")
    Call<ArtworkResponse> getAllArtworks(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);
}
