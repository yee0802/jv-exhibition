package com.example.jv_exhibition.ui.mainactivity;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jv_exhibition.model.Artwork;
import com.example.jv_exhibition.model.Exhibition;

import java.util.List;

public class ArtworkClickHandler {
    private final Context context;
    private final MainActivityViewModel viewModel;

    public ArtworkClickHandler(Context context, MainActivityViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onAddClick(Artwork artwork) {
        List<Exhibition> exhibitions = viewModel.getExhibitions().getValue();

        if (exhibitions == null || exhibitions.isEmpty()) {
            new AlertDialog.Builder(context)
                    .setTitle("No exhibitions")
                    .setMessage("You don't have any exhibitions yet. Please create one first.")
                    .setPositiveButton("OK", (d, which) -> {})
                    .show();
            return;
        }

        String[] names = new String[exhibitions.size()];
        for (int i = 0; i < exhibitions.size(); i++) {
            names[i] = exhibitions.get(i).getName();
        }

        new AlertDialog.Builder(context)
                .setTitle("Choose Exhibition")
                .setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, names),
                        (dialog, which) -> {
                            Exhibition exhibition = exhibitions.get(which);
                            viewModel.addArtworkToExhibition(exhibition.getId(), artwork.getId());
                            Toast.makeText(context, String.format("%s added to %s successfully!", artwork.getTitle(), exhibition.getName()), Toast.LENGTH_SHORT).show();
                        })
                .setNegativeButton("Cancel", (d, w) -> {})
                .show();
    }
}
