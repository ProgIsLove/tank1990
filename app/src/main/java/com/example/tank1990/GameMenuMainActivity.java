package com.example.tank1990;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameMenuMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game_main);

        Button playBtn = findViewById(R.id.btn_play);
        playBtn.setOnClickListener((View v) -> openGameActivity());

        Button scoreBtn = findViewById(R.id.btn_scores);
        scoreBtn.setOnClickListener((View v) -> openHighScoreActivity());

        Button aboutBtn = findViewById(R.id.btn_about);
        aboutBtn.setOnClickListener((View v) -> openAboutActivity());

        Button endBtn = findViewById(R.id.btn_end);
        endBtn.setOnClickListener((View v) -> finish());
    }

    private void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void openHighScoreActivity() {
        Intent intent = new Intent(this, HighScoreActivity.class);
        startActivity(intent);
    }

    private void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}