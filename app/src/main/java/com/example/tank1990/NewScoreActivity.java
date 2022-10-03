package com.example.tank1990;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class NewScoreActivity extends AppCompatActivity {

    private Integer scoreValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_score);

        EditText edit_name = findViewById(R.id.input_name);
        TextView scoreView = findViewById(R.id.score_label);

        Intent intent = getIntent();
        if (intent != null) {
            scoreValue = intent.getIntExtra("score", 0);
            scoreView.setText(String.format(Locale.ENGLISH, "%d", scoreValue));
        }

        Button btn = findViewById(R.id.btn_submit);

        DAOPlayerInfo dao = new DAOPlayerInfo();

        btn.setOnClickListener((View v) -> {
            PlayerInfo playerInfo = new PlayerInfo(edit_name.getText().toString(), Integer.valueOf(scoreView.getText().toString()));
            dao.add(playerInfo).addOnSuccessListener(success -> {
                Toast.makeText(this, "Score is inserted", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(error -> {
                Toast.makeText(this, String.format(Locale.ENGLISH, "%s", error.getMessage()), Toast.LENGTH_LONG).show();
            });
            gameOverActivity(scoreValue);
        });
    }

    private void gameOverActivity(Integer score) {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}