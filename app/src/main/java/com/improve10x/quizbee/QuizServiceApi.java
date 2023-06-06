package com.improve10x.quizbee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuizServiceApi {

    @GET("renuTodo")
    Call<List<QuizApp>> fetchQuizData();
}
