package com.example.josue.cardgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Button;
import android.widget.GridLayout;

/**
 * Created by josue on 12/1/2017.
 */

@SuppressLint({"AppCompatCustomView", "ViewConstructor"})
public class Card extends Button{

    private int row;
    private int column;
    private int card_id;

    private boolean isFlipped = false;
    private boolean isMatched = false;

    protected Drawable cardfront;
    protected Drawable cardback;

    public Card (Context context, int r, int c, int id){

        super(context);

        row = r;
        column = c;
        card_id = id;

        cardfront = ResourcesCompat.getDrawable(getResources(), card_id, null);
        cardback = ResourcesCompat.getDrawable(getResources(), R.drawable.cardback, null);
        //Sets Card to the cardback
        setBackground(cardback);
        //Determines layout within the grid
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        //Sets Card Size
//        tempParams.width = (int) getResources().getDisplayMetrics().density * 125;
//        tempParams.height = (int) getResources().getDisplayMetrics().density * 150;
        tempParams.width = (int) getResources().getDisplayMetrics().density * 100;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 125;

        setLayoutParams(tempParams);
    }


    public boolean isMatched(){
        return isMatched;
    }

    public void setMatch (boolean match){
        isMatched = match;
    }

    public int getCard_id(){
        return card_id;
    }

    public void flip(){
        if(isMatched){
            return;
        }
        if(isFlipped)
        {
            setBackground(cardback);
            isFlipped = false;
        }
        else{
            setBackground(cardfront);
            isFlipped = true;
        }
    }


}
