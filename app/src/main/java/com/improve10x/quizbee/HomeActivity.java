package com.improve10x.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.quizbee.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
        private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}