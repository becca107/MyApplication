package com.example.myapplication.controller;

import android.content.DialogInterface;
import android.content.Intent; // <-- Ajouté
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Question;
import com.example.myapplication.model.QuestionBank;

import java.util.Arrays;


public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView m_textViewQuestion;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Question mCurrentQuestion;
    private final QuestionBank mQuestionBank= generateQuestions();
    private int mRemainingQuestionCount;
    private int mScore;
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;
    public static final String BUNDLE_STATE_SCORE = "BUNDLE_STATE_SCORE";
    public static final String BUNDLE_STATE_QUESTION = "BUNDLE_STATE_QUESTION";


    private QuestionBank generateQuestions() {
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );
        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );
        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"
                ),

                3
        );
        Question question4 = new Question(
                "What is the capital of Australia?",
                Arrays.asList(
                        "Sydney",
                        "Melbourne",
                        "Canberra",
                        "Perth"
                ),
                2
        );

        Question question5 = new Question(
                "Who painted the Mona Lisa?",
                Arrays.asList(
                        "Vincent Van Gogh",
                        "Leonardo da Vinci",
                        "Pablo Picasso",
                        "Claude Monet"
                ),
                1
        );

        Question question6 = new Question(
                "Which element has the chemical symbol 'O'?",
                Arrays.asList(
                        "Gold",
                        "Oxygen",
                        "Silver",
                        "Iron"
                ),
                1
        );

        Question question7 = new Question(
                "What year did the Titanic sink?",
                Arrays.asList(
                        "1912",
                        "1920",
                        "1905",
                        "1918"
                ),
                0
        );

        Question question8 = new Question(
                "Which planet is known as the Red Planet?",
                Arrays.asList(
                        "Venus",
                        "Jupiter",
                        "Mars",
                        "Saturn"
                ),
                2
        );

        Question question9 = new Question(
                "What is the largest ocean on Earth?",
                Arrays.asList(
                        "Atlantic Ocean",
                        "Indian Ocean",
                        "Arctic Ocean",
                        "Pacific Ocean"
                ),
                3
        );

        Question question10 = new Question(
                "Who wrote 'Romeo and Juliet'?",
                Arrays.asList(
                        "Charles Dickens",
                        "William Shakespeare",
                        "Jane Austen",
                        "Mark Twain"
                ),
                1
        );

        Question question11 = new Question(
                "How many continents are there on Earth?",
                Arrays.asList(
                        "5",
                        "6",
                        "7",
                        "8"
                ),
                2
        );

        Question question12 = new Question(
                "Which country is famous for the pyramids of Giza?",
                Arrays.asList(
                        "Mexico",
                        "Egypt",
                        "Peru",
                        "India"
                ),
                1
        );

        Question question13 = new Question(
                "Which organ in the human body pumps blood?",
                Arrays.asList(
                        "Lungs",
                        "Brain",
                        "Liver",
                        "Heart"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3,
                question4, question5, question6, question7, question8,
                question9, question10, question11, question12, question13));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mRemainingQuestionCount = 6; // Set the number of questions to be asked
        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        mScore = 0; // Initialize the score

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mRemainingQuestionCount = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }else{
            mScore = 0;
            mRemainingQuestionCount = 4;
        }


        m_textViewQuestion = findViewById(R.id.game_activity_textview_question);
        mButton1 = findViewById(R.id.game_activity_button_1);
        mButton2 = findViewById(R.id.game_activity_button_2);
        mButton3 = findViewById(R.id.game_activity_button_3);
        mButton4 = findViewById(R.id.game_activity_button_4);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        displayQuestion(mCurrentQuestion);
    }
    @Override
    public void onClick(View v) {
        int index;
        if (v == mButton1) {
            index = 0;
        } else if (v == mButton2) {
            index = 1;
        } else if (v == mButton3) {
            index = 2;
        } else if (v == mButton4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view: " + v);
        }
        // Check if the answer is correct
        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++; // Increment the score for a correct answer
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                mRemainingQuestionCount--;
                if (mRemainingQuestionCount <= 0) {
                    mCurrentQuestion = mQuestionBank.getNextQuestion();
                    displayQuestion(mCurrentQuestion);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }
        }, 2000);

        mRemainingQuestionCount--;
        if (mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Well done!");
            builder.setMessage("Your score is: " + mScore + "/" + 6);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
            builder.create();
            builder.show();
        }

    }
    private void displayQuestion(final Question question) {
        m_textViewQuestion.setText(question.getQuestion());
        mButton1.setText(question.getChoiceList().get(0));
        mButton2.setText(question.getChoiceList().get(1));
        mButton3.setText(question.getChoiceList().get(2));
        mButton4.setText(question.getChoiceList().get(3));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mRemainingQuestionCount);
    }
}