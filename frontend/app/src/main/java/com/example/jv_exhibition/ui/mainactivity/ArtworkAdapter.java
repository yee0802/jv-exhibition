package com.example.jv_exhibition.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.databinding.ArtworkItemLayoutBinding;
import com.example.jv_exhibition.model.Artwork;

import java.util.List;

public class ArtworkAdapter extends RecyclerView.Adapter<ArtworkAdapter.ArtworkViewHolder>  {
    private FragmentActivity activity;
    private List<Artwork> artworkList;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public ArtworkAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Artwork> artworkList, FragmentActivity activity) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.artworkList = artworkList;
        this.activity = activity;
    }

    public static class ArtworkViewHolder extends RecyclerView.ViewHolder {
        private ArtworkItemLayoutBinding binding;

        public ArtworkViewHolder(@NonNull ArtworkItemLayoutBinding artworkItemLayoutBinding, RecyclerViewInterface recyclerViewInterface) {
            super(artworkItemLayoutBinding.getRoot());
            this.binding = artworkItemLayoutBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ArtworkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ArtworkItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.artwork_item_layout,
                parent,
                false
        );

        return new ArtworkViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewHolder holder, int position) {
        Artwork artwork = artworkList.get(position);
        holder.binding.setArtwork(artwork);
    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public void setFilteredList(List<Artwork> filteredList) {
        this.artworkList = filteredList;
        notifyDataSetChanged();
    }
}
