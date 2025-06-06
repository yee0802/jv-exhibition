package com.example.jv_exhibition.ui.exhibitions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.databinding.ExhibitionItemLayoutBinding;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.ui.mainactivity.RecyclerViewInterface;

import java.util.List;

public class ExhibitionAdapter extends RecyclerView.Adapter<ExhibitionAdapter.ExhibitionViewHolder> {
    private List<Exhibition> exhibitions;
    private final RecyclerViewInterface recyclerViewInterface;

    public ExhibitionAdapter(List<Exhibition> exhibitions, RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.exhibitions = exhibitions;
    }


    public static class ExhibitionViewHolder extends RecyclerView.ViewHolder {
        private ExhibitionItemLayoutBinding binding;

        public ExhibitionViewHolder(@NonNull ExhibitionItemLayoutBinding exhibitionItemLayoutBinding, RecyclerViewInterface recyclerViewInterface) {
            super(exhibitionItemLayoutBinding.getRoot());
            this.binding = exhibitionItemLayoutBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    public void setExhibitions(List<Exhibition> newList) {
        this.exhibitions = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExhibitionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExhibitionItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.exhibition_item_layout,
                parent,
                false
        );

        return new ExhibitionViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(ExhibitionViewHolder holder, int position) {
        Exhibition exhibition = exhibitions.get(position);
        holder.binding.setExhibition(exhibition);
    }

    @Override
    public int getItemCount() {
        return exhibitions.size();
    }
}

