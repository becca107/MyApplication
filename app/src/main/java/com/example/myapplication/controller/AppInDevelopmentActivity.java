package com.example.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AppInDevelopmentActivity extends AppCompatActivity {

    private TextView textViewAppName;
    private TextView textViewAppFeatures;
    private TextView textViewMessage;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_in_development);

        initViews();
        setupContent();
        setupClickListeners();
    }

    private void initViews() {
        textViewAppName = findViewById(R.id.textViewAppName);
        textViewAppFeatures = findViewById(R.id.textViewAppFeatures);
        textViewMessage = findViewById(R.id.textViewMessage);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void setupContent() {
        String appName = getIntent().getStringExtra("APP_NAME");
        String appFeatures = getIntent().getStringExtra("APP_FEATURES");
        String userName = getIntent().getStringExtra("USER_NAME");

        textViewAppName.setText(appName);
        textViewAppFeatures.setText("Fonctionnalités prévues :\n\n" + appFeatures);
        textViewMessage.setText("Bonjour " + userName + " !\n\n" +
                "Cette application est actuellement en cours de développement. " +
                "Nous travaillons dur pour vous offrir une expérience exceptionnelle. " +
                "Revenez bientôt pour découvrir toutes ces fonctionnalités !");
    }

    private void setupClickListeners() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Retourne à l'écran précédent
            }
        });
    }
}
