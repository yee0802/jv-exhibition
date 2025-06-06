package com.example.jv_exhibition.ui.exhibitiondetail;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.databinding.ActivityExhibitionDetailsBinding;
import com.example.jv_exhibition.model.Exhibition;

public class ExhibitionDetailActivity extends AppCompatActivity {
    private ActivityExhibitionDetailsBinding binding;
    private ExhibitionDetailViewModel viewModel;
    private ExhibitionDetailAdapter adapter;
    private Exhibition exhibition;
    private ExhibitionDetailClickHandler handler;
    private static final String EXHIBITION_KEY = "exhibition_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibition_details);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            exhibition = getIntent().getParcelableExtra(EXHIBITION_KEY, Exhibition.class);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_exhibition_details);

        viewModel = new ViewModelProvider(this).get(ExhibitionDetailViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.exhibition_page_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExhibitionDetailAdapter(this, viewModel, (v, artwork) -> {
            ExhibitionDetailClickHandler handler = new ExhibitionDetailClickHandler(this, viewModel, exhibition);
            handler.onRemoveClick(v, artwork);
        });
        recyclerView.setAdapter(adapter);

        viewModel.getExhibitionById(exhibition.getId()).observe(this, exhibition -> {
            if (exhibition != null) {
                adapter.setArtworkList(exhibition.getArtworks());
            }
        });

        handler = new ExhibitionDetailClickHandler(this, viewModel, exhibition);

        binding.setExhibition(exhibition);
        binding.setClickHandler(handler);
    }
}


