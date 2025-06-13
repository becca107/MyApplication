package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private Button buttonContinue;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonContinue = findViewById(R.id.buttonContinue);
    }

    private void setupClickListeners() {
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = editTextUserName.getText().toString().trim();

                if (userName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir votre nom", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Passer à l'écran de sélection des applications
                Intent intent = new Intent(MainActivity.this, AppMenuActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });
    }
}