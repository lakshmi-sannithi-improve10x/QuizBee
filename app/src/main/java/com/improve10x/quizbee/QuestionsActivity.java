package com.improve10x.quizbee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.improve10x.quizbee.databinding.ActivityQuestionsBinding;

public class QuestionsActivity extends AppCompatActivity {
   private ActivityQuestionsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}