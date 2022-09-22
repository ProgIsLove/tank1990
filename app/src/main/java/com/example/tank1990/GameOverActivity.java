package com.example.tank1990;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        if (intent != null) {
            Integer value = intent.getIntExtra("score", 0);
            TextView scoreView = findViewById(R.id.scoreView);
            scoreView.setText(String.format(Locale.ENGLISH, "Your score %d", value));
        }

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
}