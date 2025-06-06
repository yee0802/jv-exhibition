package com.example.jv_exhibition.ui.exhibitions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.repository.ExhibitionRepository;

import java.util.List;
public class ExhibitionViewModel extends AndroidViewModel {
    private final ExhibitionRepository repository;

    public ExhibitionViewModel(@NonNull Application application) {
        super(application);
        repository = new ExhibitionRepository(application);
    }
    public LiveData<List<Exhibition>> getExhibitions() {
        return repository.getMutableLiveData();
    }

    public void createExhibition(String name) {
        repository.createExhibition(name);
    }
}

