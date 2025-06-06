package com.example.jv_exhibition.ui.exhibitions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_exhibition.R;
import com.example.jv_exhibition.model.Exhibition;
import com.example.jv_exhibition.ui.exhibitiondetail.ExhibitionDetailActivity;
import com.example.jv_exhibition.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionFragment extends Fragment implements RecyclerViewInterface {
    private ExhibitionViewModel viewModel;
    private RecyclerView recyclerView;
    private List<Exhibition> exhibitionsFromViewModel = new ArrayList<>();
    private ExhibitionAdapter adapter;
    private Button createButton;
    private EditText nameInput;
    private static final String EXHIBITION_KEY = "exhibition_key";

    public ExhibitionFragment() {
        super(R.layout.fragment_exhibition);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ExhibitionViewModel.class);
        recyclerView = view.findViewById(R.id.exhibition_recycler_view);
        nameInput = view.findViewById(R.id.exhibition_name_input);
        createButton = view.findViewById(R.id.create_exhibition_button);

        adapter = new ExhibitionAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getExhibitions().observe(getViewLifecycleOwner(), exhibitions -> {
            exhibitionsFromViewModel = exhibitions;
            adapter.setExhibitions(exhibitions);
        });

        createButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();

            if (!name.isEmpty()) {
                viewModel.createExhibition(name);
                nameInput.setText("");
            } else {
                Toast.makeText(getContext(), "Please input an Exhibition name!", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getExhibitions();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ExhibitionDetailActivity.class);
        intent.putExtra(EXHIBITION_KEY, exhibitionsFromViewModel.get(position));
        startActivity(intent);
    }
}

