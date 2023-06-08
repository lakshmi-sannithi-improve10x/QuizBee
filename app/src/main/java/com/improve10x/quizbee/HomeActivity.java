package com.improve10x.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.improve10x.quizbee.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
        private ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        getSupportActionBar().setTitle("Quiz Bee");
        setContentView(binding.getRoot());
        handleStartBtn();
    }

    private void handleStartBtn() {
        binding.startBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, QuestionsActivity.class);
            startActivity(intent);
        });
    }
}