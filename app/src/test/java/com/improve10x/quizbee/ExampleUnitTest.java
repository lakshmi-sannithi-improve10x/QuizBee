package com.improve10x.quizbee;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public  void  getQuizQuestions() throws IOException {
        QuizServiceApi quizServiceApi = new QuizApi().createQuizServiceApi();
        Call<List<QuizApp>> call = quizServiceApi.fetchQuizData();
        List<QuizApp> quizApps = call.execute().body();
        assertNotNull(quizApps);
        assertFalse(quizApps.isEmpty());
        System.out.println(new Gson().toJson(quizApps));
    }
}