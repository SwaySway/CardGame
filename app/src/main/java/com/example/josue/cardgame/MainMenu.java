package com.example.josue.cardgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainMenu extends AppCompatActivity {

    private Button startGame;
    private Button highscoreActivity;
    private ToggleButton musicToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame = findViewById(R.id.startBttn);
        highscoreActivity = findViewById(R.id.highscoreBttn);
        musicToggle = findViewById(R.id.musictoggle);
        startGame.setText("Start Game");
        highscoreActivity.setText("Highscores ");
        //Changed text for on and off
        musicToggle.setTextOff("Music Off");
        musicToggle.setTextOn("Music On");
    }


    public void startGame(View view) {
        //opens activity that asks user for how many cards they are choosing
        Intent gameIntent = new Intent(MainMenu.this, Game.class);
        startActivity(gameIntent);
    }



}
