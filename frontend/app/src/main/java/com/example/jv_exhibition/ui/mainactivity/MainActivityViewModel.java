package com.example.jv_exhibition.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jv_exhibition.model.ArtworkResponse;
import com.example.jv_exhibition.repository.ArtworkRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private ArtworkRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ArtworkRepository(application);
    }

    public LiveData<ArtworkResponse> getArtworks(int pageNo, int pageSize) {
        repository.loadArtworks(pageNo, pageSize);
        return repository.getArtworksLiveData();
    }
}
