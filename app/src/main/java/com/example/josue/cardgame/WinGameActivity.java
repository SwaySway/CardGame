package com.example.josue.cardgame;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WinGameActivity extends AppCompatActivity {

    private TextView scoreView;
    private EditText nameField;
    private Button submit;
    private int score;
    private ArrayList<Score> scores = new ArrayList<Score>();
    private ObjectOutputStream oos = null;


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
        nameField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    finalScore();
                    backtoMainMenu();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void submitScore(View view){
        finalScore();
        backtoMainMenu();
    }

    private void backtoMainMenu(){
        Intent newIntent = new Intent(WinGameActivity.this, MainMenu.class);

        startActivity(newIntent);
    }

    private void finalScore(){
      //write to file

    }



}
