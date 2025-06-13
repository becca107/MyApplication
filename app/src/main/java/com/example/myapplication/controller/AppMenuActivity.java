package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AppMenuActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private String userName;

    // Boutons pour toutes les applications
    private Button btnTodoList, btnCalculator, btnContacts, btnNotes, btnTimer;
    private Button btnConverter, btnTopQuiz, btnMusicPlayer, btnWeather, btnExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_menu);

        // Récupérer le nom d'utilisateur
        userName = getIntent().getStringExtra("USER_NAME");

        initViews();
        setupWelcomeMessage();
        setupClickListeners();
    }

    private void initViews() {
        textViewWelcome = findViewById(R.id.textViewWelcome);
        btnTodoList = findViewById(R.id.btnTodoList);
        btnCalculator = findViewById(R.id.btnCalculator);
        btnContacts = findViewById(R.id.btnContacts);
        btnNotes = findViewById(R.id.btnNotes);
        btnTimer = findViewById(R.id.btnTimer);
        btnConverter = findViewById(R.id.btnConverter);
        btnTopQuiz = findViewById(R.id.btnTopQuiz);
        btnMusicPlayer = findViewById(R.id.btnMusicPlayer);
        btnWeather = findViewById(R.id.btnWeather);
        btnExpenses = findViewById(R.id.btnExpenses);
    }

    private void setupWelcomeMessage() {
        textViewWelcome.setText("Bienvenue " + userName + " !\nChoisissez une application :");
    }

    private void setupClickListeners() {
        // Applications fonctionnelles
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppMenuActivity.this, CalculatriceActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });

        btnTopQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppMenuActivity.this, NameActivity.class);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
            }
        });

        // Applications en développement
        btnTodoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("To-Do List (Gestionnaire de tâches)",
                        "• Ajouter une tâche\n• Marquer une tâche comme achevée\n• Supprimer une tâche");
            }
        });

        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Carnet de Contacts Simplifié",
                        "• Ajouter un contact\n• Voir la liste des contacts\n• Supprimer un contact");
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Bloc-Notes Personnel",
                        "• Prise de notes avec titre et contenu\n• Sauvegarde locale");
            }
        });

        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Chronomètre / Minuteur",
                        "• Chronomètre\n• Minuteur réglable\n• Boutons démarrer / arrêter / réinitialiser");
            }
        });

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Convertisseur de devises ou unités",
                        "• Saisir une valeur\n• Choisir les unités ou devises\n• Afficher le résultat");
            }
        });

        btnMusicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Lecteur de musique simple",
                        "• Lire des fichiers MP3 locaux\n• Contrôler la lecture");
            }
        });

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Application météo simplifiée",
                        "• Saisie du nom de la ville\n• Affichage de la température et de la météo");
            }
        });

        btnExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInDevelopment("Suivi des dépenses",
                        "• Ajouter une dépense\n• Voir le total\n• Catégoriser les dépenses");
            }
        });
    }

    private void showAppInDevelopment(String appName, String features) {
        Intent intent = new Intent(AppMenuActivity.this, AppInDevelopmentActivity.class);
        intent.putExtra("APP_NAME", appName);
        intent.putExtra("APP_FEATURES", features);
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
    }
}
