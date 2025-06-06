package com.example.jv_exhibition.ui.mainactivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.databinding.FragmentHomeBinding;
import com.example.jv_exhibition.model.Artwork;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    private FragmentHomeBinding binding;
    private MainActivityViewModel mainActivityViewModel;
    private ArtworkAdapter artworkAdapter;
    private ArtworkClickHandler artworkClickHandler;
    private List<Artwork> allArtworks = new ArrayList<>();
    private List<Artwork> filteredArtworks = new ArrayList<>();
    private int currentPage = 0;
    private final int pageSize = 10;

    public HomeFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();

        mainActivityViewModel.getExhibitions();
        artworkClickHandler = new ArtworkClickHandler(requireContext(), mainActivityViewModel);

        artworkAdapter = new ArtworkAdapter(this, requireContext(), new ArrayList<>(), getActivity(), artworkClickHandler);
        binding.mainRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mainRecyclerView.setAdapter(artworkAdapter);

        loadPage();

        binding.mainBtnNext.setOnClickListener(v -> {
            if (binding.mainBtnNext.isEnabled()) {
                currentPage++;
                loadPage();
            }
        });

        binding.mainBtnPrev.setOnClickListener(v -> {
            if (binding.mainBtnPrev.isEnabled()) {
                currentPage--;
                loadPage();
            }
        });

        binding.mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (layoutManager != null) {
                    int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = artworkAdapter.getItemCount();

                    if (lastVisibleItem == totalItemCount - 1 && totalItemCount > 0) {
                        binding.mainNavButtonContainer.setVisibility(View.VISIBLE);
                    } else {
                        binding.mainNavButtonContainer.setVisibility(View.GONE);
                    }
                }
            }
        });

        setupSearchView(view);

        return view;
    }

    private void loadPage() {
        mainActivityViewModel.getArtworks(currentPage, pageSize).observe(getViewLifecycleOwner(), artworkResponse -> {
            if (artworkResponse != null) {
                List<Artwork> artworks = artworkResponse.getContent();
                allArtworks = artworks;
                artworkAdapter.setFilteredList(artworks);

                binding.mainPageNumber.setText(String.valueOf(artworkResponse.getPageNo() + 1));

                binding.mainBtnPrev.setEnabled(artworkResponse.getPageNo() > 0);
                binding.mainBtnNext.setEnabled(!artworkResponse.isLast());

                binding.mainRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    private void setupSearchView(View view) {
        SearchView searchView = view.findViewById(R.id.main_search_view);
        searchView.clearFocus();

        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(Color.WHITE);
        searchEditText.setHintTextColor(Color.WHITE);

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);
        searchIcon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

        ImageView closeIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeIcon.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String query) {
        filteredArtworks.clear();
        String queryLowerCase = query.toLowerCase();

        for (Artwork art : allArtworks) {
            if (art.getTitle().toLowerCase().contains(queryLowerCase)
                    || art.getArtist().toLowerCase().contains(queryLowerCase)) {
                filteredArtworks.add(art);
            }
        }

        if (filteredArtworks.isEmpty()) {
            Toast.makeText(getContext(), "No artworks found!", Toast.LENGTH_SHORT).show();
        }

        artworkAdapter.setFilteredList(filteredArtworks);
    }

    @Override
    public void onItemClick(int position) {}
}
