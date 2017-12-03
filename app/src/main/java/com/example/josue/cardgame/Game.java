package com.example.josue.cardgame;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private GridLayout cardlayout;
    //Buttons
    private Button tryagainBttn;
    private Button newgameBttn;
    private Button endgameBttn;

    private int numofElements;
    private Card[] cards;
    private int[] cardGraphicsLocation;
    private int[] cardGraphics;
    //Cards Used
    private Card selectedCard1;
    private Card selectedCard2;


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


    @Override
    public void onClick(View view) {

    }
}
