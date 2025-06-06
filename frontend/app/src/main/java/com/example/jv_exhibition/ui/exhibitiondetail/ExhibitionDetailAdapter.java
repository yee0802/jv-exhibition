package com.example.jv_exhibition.ui.exhibitiondetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.databinding.ExhibitionArtworkItemLayoutBinding;
import com.example.jv_exhibition.model.Artwork;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionDetailAdapter extends RecyclerView.Adapter<ExhibitionDetailAdapter.ExhibitionDetailViewHolder> {
    private List<Artwork> artworkList = new ArrayList<>();
    private final Context context;
    private final ExhibitionDetailViewModel viewModel;
    private final OnRemoveClickListener removeClickListener;

    public interface OnRemoveClickListener {
        void onRemoveClick(View view, Artwork artwork);
    }
    public ExhibitionDetailAdapter(Context context, ExhibitionDetailViewModel viewModel, OnRemoveClickListener listener) {
        this.context = context;
        this.viewModel = viewModel;
        this.removeClickListener = listener;
    }

    public static class ExhibitionDetailViewHolder extends RecyclerView.ViewHolder {
        private final ExhibitionArtworkItemLayoutBinding binding;

        public ExhibitionDetailViewHolder(ExhibitionArtworkItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ExhibitionDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ExhibitionArtworkItemLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.exhibition_artwork_item_layout, parent, false);

        return new ExhibitionDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExhibitionDetailViewHolder holder, int position) {
        Artwork artwork = artworkList.get(position);
        holder.binding.setArtwork(artwork);

        holder.binding.exhibitionArtworkRemoveBtn.setOnClickListener(v -> {
            if (removeClickListener != null) {
                removeClickListener.onRemoveClick(v, artwork);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public void setArtworkList(List<Artwork> artworks) {
        this.artworkList = artworks;
        notifyDataSetChanged();
    }
}



