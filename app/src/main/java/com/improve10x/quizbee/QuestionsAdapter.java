package com.improve10x.quizbee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.quizbee.databinding.QuestionItemBinding;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    private List<Question> questions;
    private OnItemActionListener actionListener;
    private int selectedQuestion = 0;
    public QuestionsAdapter(List<Question> questions){
        this.questions = questions;
    }

    void setActionListener(OnItemActionListener listener){
        this.actionListener = listener;
    }


    void  setData(List<Question> questions){
        this.questions = questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionItemBinding binding = QuestionItemBinding.inflate(inflater,parent,false);
        QuestionViewHolder viewHolder = new QuestionViewHolder(binding);
        return viewHolder;
    }

   @SuppressLint("ResourceAsColor")
   @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, @SuppressLint("RecyclerView") int position) {
     Question question = questions.get(position);
     holder.binding.questionNumberTxt.setText(String.valueOf(question.getNumber()));
     if (selectedQuestion == position){
         holder.binding.questionNumberTxt.setTextColor(Color.parseColor("#FFFFFF"));
     }else {
         holder.binding.questionNumberTxt.setTextColor(Color.parseColor("#000000"));
     }
     holder.binding.getRoot().setOnClickListener(view -> {
            actionListener.OnClick(question.getNumber());
            selectedQuestion = position;
            notifyDataSetChanged();
     });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
