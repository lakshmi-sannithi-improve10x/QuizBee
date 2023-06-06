package com.improve10x.quizbee;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizApp {
    @SerializedName("_id")
    private String id;
    private Module module;
    private List<Question> questions;
}
