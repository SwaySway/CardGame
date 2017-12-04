package com.example.josue.cardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WinGameActivity extends AppCompatActivity {

    private TextView scoreView;
    private EditText nameField;
    private Button submit;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_game);
        scoreView = findViewById(R.id.finalScore);
        nameField = findViewById(R.id.editText);
        submit = findViewById(R.id.submit);
        score = getIntent().getIntExtra("Score", 0);
        String result = "Score: " + String.valueOf(score)+"!";
        scoreView.setText(result);
        nameField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameField.setText("");
            }
        });
    }
}
