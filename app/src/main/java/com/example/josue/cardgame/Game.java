package com.example.josue.cardgame;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private GridLayout cardlayout;
    //Buttons
    private Button tryagainBttn;
    private Button newgameBttn;
    private Button endgameBttn;

    private int numofElements;
    private Card[] cards;
    private int[] cardGraphicsLocation;
    private ArrayList<Integer> cardGraphics;
    //Cards Used
    private Card selectedCard1;
    private Card selectedCard2;

    //check used to cancel user input while flipping card
    private boolean isBusy = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sets up format
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //connects variables to items in the xml loading from resources
        cardlayout = findViewById(R.id.gridLayout);
        tryagainBttn = findViewById(R.id.retry);
        newgameBttn = findViewById(R.id.newgame);
        endgameBttn = findViewById(R.id.endgame);
        //loads cards from resources into array
        loadCards();
        shuffleCards();
        /*
            determine orientation is for the first initial try this means this will
            accordingly handle the amount of rows and columns based on orientation

            NOTE: onconfiguration change will hanlde if orientation changes
        */
        determineOrientation();
        //for initial testing we trying out a 4x4 card layout
        numofElements = 2 * 2;
    }

    //method used to store cards in a data structure
    private void loadCards(){
        cardGraphics = new ArrayList<Integer>();
        cardGraphics.add(R.drawable.card1);
        cardGraphics.add(R.drawable.card2);
        cardGraphics.add(R.drawable.card3);
        cardGraphics.add(R.drawable.card4);
        cardGraphics.add(R.drawable.card5);
        cardGraphics.add(R.drawable.card6);
        cardGraphics.add(R.drawable.card7);
        cardGraphics.add(R.drawable.card8);
        cardGraphics.add(R.drawable.card9);
        cardGraphics.add(R.drawable.card10);
    }

    private void shuffleCards(){

        //shuffles cards

    }


    private void determineOrientation() {
        //default code format for a 4x4 in portrait in in landscape
        //code should be able to handle 4-20 (even only)
        numofElements = cardlayout.getColumnCount() * cardlayout.getRowCount();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            cardlayout.setColumnCount(4);
            cardlayout.setRowCount(1);
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            cardlayout.setColumnCount(2);
            cardlayout.setRowCount(2);
        }
    }




    @Override
    public void onClick(View view) {

    }
}
