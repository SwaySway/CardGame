package com.example.josue.cardgame;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class Game extends AppCompatActivity {

    private GridLayout cardlayout;
    private Button tryagainBttn;
    private Button newgameBttn;
    private Button endgameBttn;

    private Card[] cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        cardlayout = findViewById(R.id.gridLayout);
        tryagainBttn = findViewById(R.id.retry);
        newgameBttn = findViewById(R.id.newgame);
        endgameBttn = findViewById(R.id.endgame);
        determineOrientation();
    }

    private void determineOrientation() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            cardlayout.setColumnCount(4);
            cardlayout.setRowCount(1);
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            cardlayout.setColumnCount(2);
            cardlayout.setRowCount(2);
        }
    }


    private void fillTable(int cards){
        for(int i = 0; i < cards; i++){
            //code to fill in card objects
        }
    }



}
