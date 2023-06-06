package com.improve10x.quizbee;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    private Integer number;
    private String question;
    private List<String> answers;
    @SerializedName("correct_answer")
    private Integer correctAnswer;
}
