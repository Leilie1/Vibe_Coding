package com.leilie.platformgame;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartGame = findViewById(R.id.btnStartGame);
        Button btnLevelSelect = findViewById(R.id.btnLevelSelect);

        btnStartGame.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("level", "level1.json");
            startActivity(intent);
        });

        btnLevelSelect.setOnClickListener(v -> showLevelSelector());
    }

    private void showLevelSelector() {
        String[] levelFiles;
        try {
            String[] allFiles = getAssets().list("");
            java.util.ArrayList<String> levelList = new java.util.ArrayList<>();
            for (String file : allFiles) {
                if (file.endsWith(".json")) {
                    levelList.add(file);
                }
            }
            levelFiles = levelList.toArray(new String[0]);
        } catch (IOException e) {
            levelFiles = new String[]{"level1.json"};
        }
        final String[] levels = levelFiles;

        new AlertDialog.Builder(this)
            .setTitle("Sélectionner un niveau")
            .setItems(levels, (dialog, which) -> {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("level", levels[which]);
                startActivity(intent);
            })
            .setNegativeButton("Annuler", null)
            .show();
    }
}
