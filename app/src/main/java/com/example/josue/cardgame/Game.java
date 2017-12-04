package com.example.josue.cardgame;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private GridLayout cardlayout;
    //Buttons
    private Button tryagainBttn;
    private Button newgameBttn;
    private Button endgameBttn;
    //Neccessary Textview
    private TextView scoreCount;
    /*
        Variables used to determine
            -column size
            -row size
            -Num of Cards
     */
    private int columnSize;
    private int rowSize;
    //Variables used for tracking
    private int numofElements;
    private Card[] cards;
    private int[] cardGraphicsLocation;
    private ArrayList<Integer> cardGraphics;
    //Cards Used when selected
    private Card selectedCard1;
    private Card selectedCard2;
    //number used to store score
    private int score;

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
        scoreCount = findViewById(R.id.scoreTitle);
        tryagainBttn.setText("Try Again");
        newgameBttn.setText("New Game");
        endgameBttn.setText("End Game");
        scoreCount.setText("Attempts: ");


        //for initial testing we trying out a 4x4 card layout
        numofElements = 4;
        rowSize = numofElements/2;
        columnSize = numofElements/2;
        cardGraphicsLocation = new int[numofElements];
        cards = new Card[numofElements];

        //loads cards from resources into array
        loadCards();
        shuffleCards();

        for(int row = 0; row < rowSize; row++){
            for(int column = 0; column < columnSize; column++){
                Card newCard = new Card(this, row, column, cardGraphics.get(cardGraphicsLocation[row*columnSize + column ]));
                newCard.setId(View.generateViewId());
                newCard.setOnClickListener(this);
                cards[row * columnSize + column] = newCard;
                cardlayout.addView(newCard);
            }
        }

        /*
            determine orientation is for the first initial try this means this will
            accordingly handle the amount of rows and columns based on orientation

            NOTE: onconfiguration change will hanlde if orientation changes
        */
        //determineOrientation();
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

    //shuffles cards in the new array
    private void shuffleCards(){
        Random rng = new Random();

        for(int i = 0; i < numofElements; i++){
            cardGraphicsLocation[i] = i % (numofElements/2);
        }
        for(int i = 0; i < numofElements; i++){
            int temp = cardGraphicsLocation[i];
            int swapIndex = rng.nextInt(numofElements);
            cardGraphicsLocation[i] = cardGraphicsLocation[swapIndex];

            cardGraphicsLocation[swapIndex] = temp;
        }
    }


    private void determineOrientation() {
        //default code format for a 4x4 in portrait in in landscape
        //code should be able to handle 4-20 (even only)
        numofElements = cardlayout.getColumnCount() * cardlayout.getRowCount();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            cardlayout.setColumnCount(4);
            cardlayout.setRowCount(2);
        }
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            cardlayout.setColumnCount(2);
            cardlayout.setRowCount(2);
        }
    }


    @Override
    public void onClick(View view) {
       if(view instanceof Card){
           if(isBusy){
               return;
           }
           Card currentCard = (Card) view;
           if(currentCard.isMatched()){
                return;
           }
           if(selectedCard1 == null){
               selectedCard1  = currentCard;
               selectedCard1.flip();
               return;
           }
           if(selectedCard1.getId() == currentCard.getId()){
               return;
           }
           //if user get's a match!
           if(selectedCard1.getCard_id() == currentCard.getCard_id()){
               currentCard.flip();

               currentCard.setMatch(true);
               selectedCard1.setMatch(true);

               selectedCard1.setEnabled(false);
               currentCard.setEnabled(false);

               selectedCard1 = null;
               return;
           }
           //got the matches wrong
           //so we need to show the user
           else{
               selectedCard2 = currentCard;
               selectedCard2.flip();
               isBusy = true;

               final Handler handler = new Handler();

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       selectedCard2.flip();
                       selectedCard1.flip();
                       selectedCard2 = null;
                       selectedCard1 = null;
                       isBusy = false;
                   }
               }, 500);
           }
       }
       else{
           return;
       }
    }
}
