package com.example.jv_exhibition.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.jv_exhibition.model.ArtworkDTO;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.model.ExhibitionDTO;
import com.example.jv_exhibition.service.ApiService;
import com.example.jv_exhibition.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExhibitionRepository {
    private final ApiService apiService;
    private MutableLiveData<List<Exhibition>> exhibitionListLiveData = new MutableLiveData<>();
    private MutableLiveData<Exhibition> exhibitionLiveData = new MutableLiveData<>();
    private final String TAG = "ExhibitionRepository";

    public ExhibitionRepository(Application application) {
        this.apiService = RetrofitInstance.getService();
    }

    public MutableLiveData<List<Exhibition>> getMutableLiveData() {
        Call<List<Exhibition>> call = apiService.getAllExhibitions();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Exhibition>> call, Response<List<Exhibition>> response) {
                List<Exhibition> exhibitions = response.body();
                exhibitionListLiveData.setValue(exhibitions);
            }

            @Override
            public void onFailure(Call<List<Exhibition>> call, Throwable t) {
                Log.e(TAG, "Error fetching all exhibitions", t);
            }
        });

        return exhibitionListLiveData;
    }

    public MutableLiveData<Exhibition> getExhibitionById(Long id) {
        Call<Exhibition> call = apiService.getExhibitionById(id);

        call.enqueue(new Callback<Exhibition>() {
            @Override
            public void onResponse(Call<Exhibition> call, Response<Exhibition> response) {
                Exhibition exhibition = response.body();
                exhibitionLiveData.setValue(exhibition);
            }

            @Override
            public void onFailure(Call<Exhibition> call, Throwable t) {
                Log.e(TAG, "Error while getting exhibition by id", t);
            }
        });

        return exhibitionLiveData;
    }

    public void createExhibition(String name) {
        ExhibitionDTO exhibitionDTO = new ExhibitionDTO(name);

       Call<Exhibition> call = apiService.createExhibition(exhibitionDTO);

       call.enqueue(new Callback<Exhibition>() {
           @Override
           public void onResponse(Call<Exhibition> call, Response<Exhibition> response) {
               getMutableLiveData();
           }

           @Override
           public void onFailure(Call<Exhibition> call, Throwable t) {
               Log.e(TAG, "Error creating an exhibition");
           }
       });
    }

    public void addArtworkToExhibition(long exhibitionId, String artworkId, Callback<Exhibition> callback) {
        ArtworkDTO dto = new ArtworkDTO(artworkId);
        Call<Exhibition> call = apiService.addArtworkToExhibition(exhibitionId, dto);

        call.enqueue(callback);
    }

    public void removeArtworkFromExhibition(long exhibitionId, String artworkId, Callback<Exhibition> callback) {
        ArtworkDTO dto = new ArtworkDTO(artworkId);
        Call<Exhibition> call = apiService.removeArtworkFromExhibition(exhibitionId, dto);

        call.enqueue(callback);
    }
}
