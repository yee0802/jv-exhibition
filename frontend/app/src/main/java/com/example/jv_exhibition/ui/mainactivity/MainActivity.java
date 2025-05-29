package com.example.jv_exhibition.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jv_exhibition.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_nav_bar);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.nav_bar_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_bar_home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, new HomeFragment())
                    .commit();
            return true;
        }

        // TODO: Implement saved Exhibitions tab
//        if (item.getItemId() == R.id.nav_bar_saved) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.main_frame_layout, new SavedArtworksFragment())
//                    .commit();
//            return true;
//        }

        return false;
    }
}
