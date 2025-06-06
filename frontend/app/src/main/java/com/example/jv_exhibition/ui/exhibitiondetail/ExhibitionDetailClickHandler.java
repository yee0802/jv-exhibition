package com.example.jv_exhibition.ui.exhibitiondetail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.jv_exhibition.model.Artwork;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.ui.mainactivity.MainActivity;

public class ExhibitionDetailClickHandler {
    private Context context;
    private final ExhibitionDetailViewModel viewModel;
    private Exhibition exhibition;

    public ExhibitionDetailClickHandler(Context context, ExhibitionDetailViewModel viewModel, Exhibition exhibition) {
        this.context = context;
        this.viewModel = viewModel;
        this.exhibition = exhibition;
    }

    public void onBackButtonClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onRemoveClick(View view, Artwork artwork) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("Delete this Artwork?")
                .setMessage("This will delete " + artwork.getTitle() + " from this exhibition " + " permanently.")
                .setPositiveButton("Delete", (dialog, id) -> {
                    Intent intent = new Intent(context, MainActivity.class);
                    viewModel.removeArtworkFromExhibition(exhibition.getId(), artwork.getId());
                    Toast.makeText(context, String.format("%s removed from %s successfully!", artwork.getTitle(), exhibition.getName()), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                })
                .setNegativeButton("Cancel", (dialog, which) -> { });
        alertBuilder.show();
    }
}
