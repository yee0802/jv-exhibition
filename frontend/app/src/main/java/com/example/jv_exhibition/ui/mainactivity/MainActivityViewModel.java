package com.example.jv_exhibition.ui.mainactivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jv_exhibition.model.Artwork;
import com.example.jv_exhibition.model.ArtworkResponse;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.repository.ArtworkRepository;
import com.example.jv_exhibition.repository.ExhibitionRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private ArtworkRepository artworkRepository;
    private ExhibitionRepository exhibitionRepository;
    private final MutableLiveData<List<Artwork>> artworksLiveData = new MutableLiveData<>();
    private final String TAG = "MainActivityViewModel";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.artworkRepository = new ArtworkRepository(application);
        this.exhibitionRepository = new ExhibitionRepository(application);
    }

    public LiveData<ArtworkResponse> getArtworks(int pageNo, int pageSize) {
        artworkRepository.loadArtworks(pageNo, pageSize);
        return artworkRepository.getArtworksLiveData();
    }

    public LiveData<List<Exhibition>> getExhibitions() {
        return exhibitionRepository.getMutableLiveData();
    }


    public void addArtworkToExhibition(long exhibitionId, String artworkId) {
        exhibitionRepository.addArtworkToExhibition(exhibitionId, artworkId, new Callback<Exhibition>() {
            @Override
            public void onResponse(Call<Exhibition> call, Response<Exhibition> response) {
                if (response.body() != null) {
                    artworksLiveData.postValue(response.body().getArtworks());
                }
            }

            @Override
            public void onFailure(Call<Exhibition> call, Throwable t) {
                Log.e(TAG, "Error while adding artwork to exhibition", t);
            }
        });
    }
}
