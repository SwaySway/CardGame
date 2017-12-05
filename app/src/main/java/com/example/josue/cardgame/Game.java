package com.example.josue.cardgame;

import android.content.Intent;
import android.content.res.Configuration;
import android.nfc.Tag;
import android.os.Handler;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private GridLayout cardlayout;
    //Buttons
    private Button tryagainBttn;
    private Button newgameBttn;
    private Button endgameBttn;

    /*
        Variables used to determine
            -column size
            -row size
            -Num of Cards
     */
    private int columnSize;
    private int rowSize;
    private int winCount = 0;
    //Variables used for tracking
    private int numofElements;
    private Card[] cards;
    private Card[] cardsLayout;
    private ArrayList<Card> cardQ;
    private int[] cardGraphicsLocation;
    private ArrayList<Integer> cardGraphics;
    //Cards Used when selected
    private Card selectedCard1;
    private Card selectedCard2;
    //number used to store score
    private int score = 0;

    //Save State


    //check used to cancel user input while flipping card
    private boolean isBusy = false;
    protected static boolean flag = true;


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
        tryagainBttn.setText("Try Again");
        newgameBttn.setText("New Game");
        endgameBttn.setText("End Game");
        cardQ = new ArrayList<Card>();
        if(flag){
            Log.d("ONCREATE", "flag is true");
        }

        //grabs number of cards from the seek bar
        numofElements = getIntent().getIntExtra("numofElements", 0);
        determineRC();
        cardGraphicsLocation = new int[numofElements];
        cards = new Card[numofElements];
        cardsLayout = new Card[numofElements];
        //loads cards from resources into array
        loadCards();
        shuffleCards();

        for(int row = 0; row < rowSize; row++){
            for(int column = 0; column < columnSize; column++){
                int tempIndex = row*columnSize + column;
                if(!(tempIndex >= numofElements)){
                    Card newCard = new Card(this, row, column, cardGraphics.get(cardGraphicsLocation[tempIndex]));
                    cardQ.add(newCard);
                    newCard.setId(View.generateViewId());
                    newCard.setOnClickListener(this);
                    cards[row * columnSize + column] = newCard;
                    cardlayout.addView(newCard);
                }
            }
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        Log.d("ONCONFIGCHANGE", "CALLED");
        flag = false;
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            if(numofElements > 8) {
                cardlayout.removeAllViews();
                for(int row = 0; row < 3; row++){
                    for(int column = 0; column < 6; column++){
                        int tempIndex = row*6 + column;
                        if(!(tempIndex >= numofElements)){
                            Card tempCard = cardQ.remove(0);
                            tempCard.changetoLandscape(row, column);
                            cardQ.add(tempCard);
                            cardlayout.addView(tempCard);
                        }
                    }
                }
            }
            }

        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if(numofElements > 8) {
                cardlayout.removeAllViews();
                for(int row = 0; row < rowSize; row++){
                    for(int column = 0; column < columnSize; column++){
                        int tempIndex = row*columnSize + column;
                        if(!(tempIndex >= numofElements)){
                            Card tempCard = cardQ.remove(0);
                            tempCard.changetoPortait(row, column);
                            cardQ.add(tempCard);
                            cardlayout.addView(tempCard);
                        }
                    }
                }
                }

                }
            }


    private void determineRC(){
        switch(numofElements){
            case 4:
                columnSize = 2;
                rowSize = 2;
                break;
            case 6:
                columnSize = 4;
                rowSize = 4;
                break;
            case 8:
                columnSize = 4;
                rowSize = 2;
                break;
            case 10:
                columnSize = 4;
                rowSize = 4;
                break;
            case 12:
                columnSize = 3;
                rowSize  = 4;
                break;
            case 14:
                columnSize = 4;
                rowSize = 4;
                break;
            case 16:
                columnSize = 4;
                rowSize = 4;
                break;
            case 18:
                columnSize = 4;
                rowSize = 4;
                break;
            case 20:
                columnSize = 5;
                rowSize = 4;
                break;
        }

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

    public void tryAgain(View view){
        if(selectedCard2 == null && selectedCard1 != null){
            selectedCard1.flip();
            selectedCard1 = null;
        }
        else if (selectedCard1 != null && selectedCard2 != null) {
            selectedCard2.flip();
            selectedCard1.flip();
            selectedCard2 = null;
            selectedCard1 = null;
            isBusy = false;
        }else{
            return;
        }
    }

    public void newGame(View view){
        Intent newIntent = new Intent(Game.this, Game.class);
        newIntent.putExtra("numofElements", numofElements);
        startActivity(newIntent);
    }
    public void endGame(View view) {
        Intent newIntent = new Intent(Game.this, WinGameActivity.class);
        newIntent.putExtra("Score", score);
        newIntent.putExtra("NumofCards", numofElements);
        startActivity(newIntent);
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
               score = score + 2;
               winCount++;
               if(winCount == (numofElements/2)){
                   Intent newIntent = new Intent(Game.this, WinGameActivity.class);
                   newIntent.putExtra("Score", score);
                   newIntent.putExtra("NumofCards", numofElements);
                   startActivity(newIntent);
               }
               return;
           }
           //got the matches wrong
           //so we need to show the user
           else{
               if(score > 0){
                   score = score - 1;
               }
               selectedCard2 = currentCard;
               selectedCard2.flip();
               isBusy = true;

           }
       }
       else{
           return;
       }
    }
}
