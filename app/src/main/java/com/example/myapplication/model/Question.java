package com.example.myapplication.model;

import java.util.List;

public class Question {
    private final String m_Question;
    private final List<String> m_ChoiceList;
    private final int m_AnswerIndex;

    public Question(String mQuestion, List<String> mChoiceList, int mAnswerIndex) {
        this.m_Question = mQuestion;
        this.m_ChoiceList = mChoiceList;
        this.m_AnswerIndex = mAnswerIndex;
    }

    public String getQuestion() {
        return m_Question;
    }

    public List<String> getChoiceList() {
        return m_ChoiceList;
    }

    public int getAnswerIndex() {
        return m_AnswerIndex;
    }
}
