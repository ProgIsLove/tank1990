package com.example.tank1990;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Button button = findViewById(R.id.btn_back);
        button.setOnClickListener((View v) -> openGameMenuActivity());
    }

    private void openGameMenuActivity() {
        Intent intent = new Intent(this, GameMenuMainActivity.class);
        startActivity(intent);
    }
}