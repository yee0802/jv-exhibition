package com.example.jv_exhibition.utils.bindings;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.jv_exhibition.R;

public class GlideBindingAdapter {
    @BindingAdapter(value = {"imageUrl"}, requireAll = false)
    public static void getImageUrl(ImageView view, @Nullable String url) {
        if (url != null && !url.trim().isEmpty()) {
            Glide.with(view.getContext())
                    .load(url)
                    .into(view);
        } else {
            view.setImageResource(R.drawable.default_artwork_cover);
        }
    }
}

