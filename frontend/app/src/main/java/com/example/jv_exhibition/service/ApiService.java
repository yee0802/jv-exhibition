package com.example.jv_exhibition.service;

import com.example.jv_exhibition.model.ArtworkDTO;
import com.example.jv_exhibition.model.ArtworkResponse;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.model.ExhibitionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("artworks")
    Call<ArtworkResponse> getAllArtworks(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("exhibitions")
    Call<List<Exhibition>> getAllExhibitions();

    @GET("exhibitions/{id}")
    Call<Exhibition> getExhibitionById(@Path("id") Long id);

    @POST("exhibitions")
    Call<Exhibition> createExhibition(@Body ExhibitionDTO exhibitionDTO);

    @POST("exhibitions/{id}/add")
    Call<Exhibition> addArtworkToExhibition(@Path("id") Long id, @Body ArtworkDTO artworkDTO);

    @POST("exhibitions/{id}/remove")
    Call<Exhibition> removeArtworkFromExhibition(@Path("id") Long id, @Body ArtworkDTO artworkDTO);
}
