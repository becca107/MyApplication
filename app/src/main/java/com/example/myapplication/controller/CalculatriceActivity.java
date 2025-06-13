package com.example.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CalculatriceActivity extends AppCompatActivity {
    private TextView m_textViewResult;
    private String m_currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private boolean isOperationPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatrice);

        // Initialisation du TextView résultat
        m_textViewResult = findViewById(R.id.textViewResult);
        m_textViewResult.setText("0");

        // Initialisation des boutons numériques
        setupNumberButtons();

        // Initialisation des boutons d'opération
        setupOperationButtons();

        // Bouton Clear
        Button buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
        // Bouton Égal
        Button buttonEqual = findViewById(R.id.buttonEqual);
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }
    private void setupNumberButtons() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        for (int i = 0; i < numberButtonIds.length; i++) {
            final int number = i;
            Button button = findViewById(numberButtonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumberClick(String.valueOf(number));
                }
            });
        }
    }
    private void setupOperationButtons() {
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperationClick("+");
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperationClick("-");
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperationClick("×");
            }
        });
    }
    private void onNumberClick(String number) {
        if (isOperationPressed) {
            m_currentNumber = "";
            isOperationPressed = false;
        }

        if (m_currentNumber.equals("0")) {
            m_currentNumber = number;
        } else {
            m_currentNumber += number;
        }

        m_textViewResult.setText(m_currentNumber);
    }
    private void onOperationClick(String op) {
        if (!m_currentNumber.isEmpty()) {
            if (!operation.isEmpty() && !isOperationPressed) {
                calculateResult();
            }

            firstNumber = Double.parseDouble(m_currentNumber);
            operation = op;
            isOperationPressed = true;
        }
    }
    private void calculateResult() {
        if (!operation.isEmpty() && !m_currentNumber.isEmpty()) {
            double secondNumber = Double.parseDouble(m_currentNumber);
            double result = 0;

            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    result = firstNumber * secondNumber;
                    break;
            }

            // Afficher le résultat sans décimales si c'est un nombre entier
            if (result == (long) result) {
                m_textViewResult.setText(String.valueOf((long) result));
                m_currentNumber = String.valueOf((long) result);
            } else {
                m_textViewResult.setText(String.valueOf(result));
                m_currentNumber = String.valueOf(result);
            }

            operation = "";
            isOperationPressed = true;
        }
    }

    private void clear() {
        m_currentNumber = "";
        operation = "";
        firstNumber = 0;
        isOperationPressed = false;
        m_textViewResult.setText("0");
    }

}