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

        Button button = findViewById(R.id.btn_play);
        button.setOnClickListener((View v) -> openGameActivity());

        Button button2 = findViewById(R.id.btn_scores);
        button2.setOnClickListener((View v) -> openHighScoreActivity());

        Button button3 = findViewById(R.id.btn_about);
        button3.setOnClickListener((View v) -> openAboutActivity());
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