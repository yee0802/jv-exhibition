package com.example.jv_exhibition.ui.exhibitiondetail;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.jv_exhibition.model.Artwork;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.repository.ExhibitionRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExhibitionDetailViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Artwork>> artworksLiveData = new MutableLiveData<>();
    private final ExhibitionRepository repository;
    private final String TAG = "ExhibitionDetailViewModel";

    public ExhibitionDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new ExhibitionRepository(application);
    }

    public LiveData<Exhibition> getExhibitionById(long exhibitionId) {
        return repository.getExhibitionById(exhibitionId);
    }

    public void removeArtworkFromExhibition(long exhibitionId, String artworkId) {
        repository.removeArtworkFromExhibition(exhibitionId, artworkId, new Callback<>() {
            @Override
            public void onResponse(Call<Exhibition> call, Response<Exhibition> response) {
                if (response.body() != null) {
                    artworksLiveData.postValue(response.body().getArtworks());
                }
            }

            @Override
            public void onFailure(Call<Exhibition> call, Throwable t) {
                Log.e(TAG, "Error while removing artwork from exhibition");
            }
        });
    }
}

