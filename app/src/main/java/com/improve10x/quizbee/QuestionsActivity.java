package com.improve10x.quizbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.improve10x.quizbee.databinding.ActivityQuestionsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity implements OnItemActionListener {
    private ActivityQuestionsBinding binding;
    private List<QuizApp> quizApps = new ArrayList<>();
    private QuestionsAdapter adapter;
    private int currentQuestionNumber = 1;

    private Integer[] answerOptionIndexes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupAdapter();
        connectAdapter();
        getApi();
        handleNextBtn();
        handlePreviousBtn();
        handleOptions();
    }

    private void updateQuestionDetails(int questionNumber) {
        currentQuestionNumber = questionNumber;
        Question question = quizApps.get(0).getQuestions().get(questionNumber - 1);
        binding.optionsRg.clearCheck();
        binding.questionTxt.setText(question.getQuestion());
        binding.optiononeRb.setText(question.getAnswers().get(0));
        binding.optiontwoRb.setText(question.getAnswers().get(1));
        binding.optionthreeRb.setText(question.getAnswers().get(2));
        binding.optionfourRb.setText(question.getAnswers().get(3));
        if (answerOptionIndexes[currentQuestionNumber] == -1) {
        } else if (answerOptionIndexes[currentQuestionNumber] == 0) {
            binding.optiononeRb.setChecked(true);
        } else if (answerOptionIndexes[currentQuestionNumber] == 1) {
            binding.optiontwoRb.setChecked(true);
        } else if (answerOptionIndexes[currentQuestionNumber] == 2) {
            binding.optionthreeRb.setChecked(true);
        } else if (answerOptionIndexes[currentQuestionNumber] == 3) {
            binding.optionfourRb.setChecked(true);
        }
    }

    private void handlePreviousBtn() {
        binding.previousBtn.setOnClickListener(view -> {
            currentQuestionNumber--;
            loadQuestionDetails(currentQuestionNumber);
        });
    }

    private void handleNextBtn() {
        binding.nextBtn.setOnClickListener(view -> {
            currentQuestionNumber++;
            loadQuestionDetails(currentQuestionNumber);
        });
    }

    private void getApi() {
        QuizServiceApi quizServiceApi = new QuizApi().createQuizServiceApi();
        Call<List<QuizApp>> call = quizServiceApi.fetchQuizData();
        call.enqueue(new Callback<List<QuizApp>>() {
            @Override
            public void onResponse(Call<List<QuizApp>> call, Response<List<QuizApp>> response) {
                Toast.makeText(QuestionsActivity.this, "Success", Toast.LENGTH_SHORT).show();
                quizApps = response.body();
                adapter.setData(quizApps.get(0).getQuestions());
                answerOptionIndexes = new Integer[quizApps.get(0).getQuestions().size()];
                updateQuestionDetails(1);
            }

            @Override
            public void onFailure(Call<List<QuizApp>> call, Throwable t) {
                Toast.makeText(QuestionsActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connectAdapter() {
        binding.querstionsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.querstionsRv.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new QuestionsAdapter(new ArrayList<>());
        adapter.setActionListener(this);
    }

    private void loadQuestionDetails(int questionNumber) {
        updateQuestionDetails(questionNumber);
        updateQuestionNumber(questionNumber);
        nextBtnStyling();
        previousBtnStyling();
    }

    @Override
    public void OnClick(int questionNumber) {
        loadQuestionDetails(questionNumber);
    }

    private void updateQuestionNumber(int questionNumber) {
        adapter.selectedQuestion = questionNumber;
        adapter.notifyDataSetChanged();
    }

    private void nextBtnStyling() {
        if (currentQuestionNumber == quizApps.get(0).getQuestions().size()) {
            binding.nextBtn.setEnabled(false);
        } else {
            binding.nextBtn.setEnabled(true);

        }
    }

    private void previousBtnStyling() {
        if (currentQuestionNumber == 1) {
            binding.previousBtn.setEnabled(false);
        } else {
            binding.previousBtn.setEnabled(true);
        }
    }

    private void handleOptions() {
        binding.optionsRg.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            if (binding.optiononeRb.isChecked()) {
                answerOptionIndexes[currentQuestionNumber] = 0;
            } else if (binding.optiontwoRb.isChecked()) {
                answerOptionIndexes[currentQuestionNumber] = 1;
            } else if (binding.optionthreeRb.isChecked()) {
                answerOptionIndexes[currentQuestionNumber] = 2;
            } else if (binding.optionfourRb.isChecked()) {
                answerOptionIndexes[currentQuestionNumber] = 3;
            }

        });
    }
}