package com.example.jv_exhibition.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jv_exhibition.model.ArtworkResponse;
import com.example.jv_exhibition.service.ApiService;
import com.example.jv_exhibition.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtworkRepository {
    private final String TAG = "ArtworkRepository";
    private final ApiService apiService;
    private MutableLiveData<ArtworkResponse> artworkResponseLiveData = new MutableLiveData<>();

    public ArtworkRepository(Application application) {
        this.apiService = RetrofitInstance.getService();
    }

    public void loadArtworks(int pageNo, int pageSize) {
        Call<ArtworkResponse> call = apiService.getAllArtworks(pageNo, pageSize);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ArtworkResponse> call, Response<ArtworkResponse> response) {
                artworkResponseLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArtworkResponse> call, Throwable t) {
                Log.e(TAG, "Failed to fetch artworks", t);
            }
        });
    }

    public MutableLiveData<ArtworkResponse> getArtworksLiveData() {
        return artworkResponseLiveData;
    }
}
