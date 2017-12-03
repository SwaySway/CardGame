package com.example.josue.cardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Highscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);


        TextView highscoreLabel = (TextView) findViewById(R.id.highscoreLabel);
        TextView highscore1 = (TextView) findViewById(R.id.highScore1);
        TextView highscore2 = (TextView) findViewById(R.id.highScore2);
        TextView highscore3 = (TextView) findViewById(R.id.highScore3);
        TextView highscore4 = (TextView) findViewById(R.id.highScore4);
        TextView highscore5 = (TextView) findViewById(R.id.highScore5);
    }
}
