package com.example.tank1990;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Button againBtn = findViewById(R.id.btn_again);
        againBtn.setOnClickListener((View v) -> {
            resetGame();
            playAgain();
        });

        Button exitBtn = findViewById(R.id.btn_quit);
        exitBtn.setOnClickListener((View v) -> finish());
    }

    private void playAgain() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void resetGame() {
        Hud hud = new Hud();
        hud.setCrownLive(1);
        hud.setLive(3);
        hud.setScore(0);
        hud.setEnemyCount(0);
    }

    private void exitGame() {
        finish();
        System.exit(0);
    }
}