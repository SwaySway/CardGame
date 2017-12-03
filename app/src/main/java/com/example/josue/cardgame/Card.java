package com.example.josue.cardgame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.widget.GridLayout;
import android.widget.TableLayout;

/**
 * Created by josue on 12/1/2017.
 */

public class Card extends AppCompatImageView {

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

        cardback = ResourcesCompat.getDrawable(getResources(), R.drawable.cardback, null);
        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
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
            //code portion for matching cards
        }
        if(isFlipped)
        {
            setBackground(cardback);
            isFlipped = false;
        }
        else{
            setBackground(cardfront);
            isFlipped = false;
        }
    }


}
