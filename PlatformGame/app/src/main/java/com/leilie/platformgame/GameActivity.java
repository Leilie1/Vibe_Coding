package com.leilie.platformgame;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private GameEngine gameEngine;
    private SensorManager sensorManager;
    private AudioManager audioManager;
    private TextView tvCoins;
    private Button btnPause;
    private boolean isPaused = false;
    private boolean dialogShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = findViewById(R.id.gameView);
        tvCoins = findViewById(R.id.tvCoins);
        btnPause = findViewById(R.id.btnPause);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        String levelFile = getIntent().getStringExtra("level");
        Level level = LevelLoader.loadLevel(this, levelFile != null ? levelFile : "level1.json");
        if (level == null) {
            level = LevelLoader.createDefaultLevel();
        }

        gameEngine = new GameEngine(level);
        gameView.setGameEngine(gameEngine);

        sensorManager = new SensorManager(this, tilt -> {
            if (!isPaused && gameEngine != null) {
                Player player = gameEngine.getPlayer();
                if (tilt > 0.5f) {
                    player.moveLeft(tilt);
                } else if (tilt < -0.5f) {
                    player.moveRight(-tilt);
                }
            }
        });

        audioManager = new AudioManager(volume -> {
            if (!isPaused && gameEngine != null && volume > 0.3f) {
                gameEngine.getPlayer().jump(volume);
            }
        });

        btnPause.setOnClickListener(v -> togglePause());

        new Thread(() -> {
            while (!isFinishing()) {
                runOnUiThread(() -> {
                    if (gameEngine != null) {
                        tvCoins.setText("Pièces: " + gameEngine.getCoinsCollected());
                        
                        if (!dialogShown && gameEngine.isGameWon()) {
                            dialogShown = true;
                            showVictoryDialog();
                        } else if (!dialogShown && gameEngine.isGameLost()) {
                            dialogShown = true;
                            showGameOverDialog();
                        }
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        gameView.startGame();
        sensorManager.start();
        audioManager.startRecording();
    }

    private void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            gameView.pauseGame();
            showPauseDialog();
        } else {
            gameView.startGame();
        }
    }

    private void showPauseDialog() {
        new AlertDialog.Builder(this)
            .setTitle(R.string.pause)
            .setMessage("Jeu en pause")
            .setPositiveButton(R.string.resume, (dialog, which) -> {
                isPaused = false;
                gameView.startGame();
            })
            .setNeutralButton(R.string.restart, (dialog, which) -> {
                dialogShown = false;
                gameEngine.reset();
                isPaused = false;
                gameView.startGame();
            })
            .setNegativeButton(R.string.menu, (dialog, which) -> finish())
            .setCancelable(false)
            .show();
    }

    private void showVictoryDialog() {
        gameView.pauseGame();
        new AlertDialog.Builder(this)
            .setTitle(R.string.victory)
            .setMessage(getString(R.string.coins_collected, gameEngine.getCoinsCollected()))
            .setPositiveButton(R.string.restart, (dialog, which) -> {
                dialogShown = false;
                gameEngine.reset();
                gameView.startGame();
            })
            .setNegativeButton(R.string.menu, (dialog, which) -> finish())
            .setCancelable(false)
            .show();
    }

    private void showGameOverDialog() {
        gameView.pauseGame();
        new AlertDialog.Builder(this)
            .setTitle(R.string.game_over)
            .setMessage("Réessayez!")
            .setPositiveButton(R.string.restart, (dialog, which) -> {
                dialogShown = false;
                gameEngine.reset();
                gameView.startGame();
            })
            .setNegativeButton(R.string.menu, (dialog, which) -> finish())
            .setCancelable(false)
            .show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pauseGame();
        sensorManager.stop();
        audioManager.stopRecording();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isPaused) {
            gameView.startGame();
            sensorManager.start();
            audioManager.startRecording();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gameView.pauseGame();
        sensorManager.stop();
        audioManager.stopRecording();
    }
}
