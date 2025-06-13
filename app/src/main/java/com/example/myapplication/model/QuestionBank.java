package com.example.myapplication.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
public class QuestionBank implements Serializable {
    private final List<Question> mQuestionList;
    private int mQuestionIndex;

    public QuestionBank(List<Question> questionList) {
        // Shuffle the question list before storing it
        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
    }

    public Question getCurrentQuestion() {
        return mQuestionList.get(mQuestionIndex);
    }

    public Question getNextQuestion() {
        // Loop over the questions and return a new one at each call
        mQuestionIndex++;
        return getCurrentQuestion();
    }

}
