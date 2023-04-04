package com.example.finalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.FragmentChangeListener;
import com.example.finalproject.Fragments.HomeFragment;
import com.example.finalproject.Fragments.LatestFragment;
import com.example.finalproject.Fragments.SearchFragment;
import com.example.finalproject.Fragments.VideoFragment;
import com.example.finalproject.R;
import com.example.finalproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {

    private static ActivityMainBinding binding;

    public int selected_fragment_number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showBottomBar();

        setVisibleFragment();

        binding.homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_fragment_number = 1;
                setVisibleFragment();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, new HomeFragment(MainActivity.this)).commit();
            }
        });

        binding.latestLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_fragment_number = 2;
                setVisibleFragment();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, new LatestFragment(MainActivity.this)).commit();
            }
        });

        binding.videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_fragment_number = 3;
                setVisibleFragment();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, new VideoFragment(MainActivity.this)).commit();
            }
        });

        binding.searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_fragment_number = 4;
                setVisibleFragment();
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, new SearchFragment()).commit();
            }
        });
    }

    private void setVisibleFragment() {

        switch (selected_fragment_number){
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment(MainActivity.this)).commit();
                binding.imageHome.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_IN);
                binding.txtHome.setTextColor(getColor(R.color.white));
                binding.imageLatest.setColorFilter(ContextCompat.getColor(this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtLatest.setTextColor(getColor(R.color.grey));
                binding.imageVideo.setColorFilter(ContextCompat.getColor(this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtVideo.setTextColor(getColor(R.color.grey));
                binding.imageSearch.setColorFilter(ContextCompat.getColor(this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtSearch.setTextColor(getColor(R.color.grey));
                break;
            case 2:
                binding.imageLatest.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white), PorterDuff.Mode.SRC_IN);
                binding.txtLatest.setTextColor(getColor(R.color.white));
                binding.imageVideo.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtVideo.setTextColor(getColor(R.color.grey));
                binding.imageHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtHome.setTextColor(getColor(R.color.grey));
                binding.imageSearch.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtSearch.setTextColor(getColor(R.color.grey));
                break;
            case 3:
                binding.imageVideo.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white), PorterDuff.Mode.SRC_IN);
                binding.txtVideo.setTextColor(getColor(R.color.white));
                binding.imageLatest.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtLatest.setTextColor(getColor(R.color.grey));
                binding.imageHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtHome.setTextColor(getColor(R.color.grey));
                binding.imageSearch.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtSearch.setTextColor(getColor(R.color.grey));
                break;
            case 4:
                binding.imageSearch.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.white), PorterDuff.Mode.SRC_IN);
                binding.txtSearch.setTextColor(getColor(R.color.white));
                binding.imageLatest.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtLatest.setTextColor(getColor(R.color.grey));
                binding.imageVideo.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtVideo.setTextColor(getColor(R.color.grey));
                binding.imageHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.grey), PorterDuff.Mode.SRC_IN);
                binding.txtHome.setTextColor(getColor(R.color.grey));
                break;
        }
    }


    public static void hideBottomBar(){
        binding.bottomBar.setVisibility(View.GONE);
    }

    public static void showBottomBar(){
        binding.bottomBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFragmentChange(int position) {
        selected_fragment_number = position;
        setVisibleFragment();
    }
}