package com.example.josue.cardgame;

import java.io.Serializable;

/**
 * Created by kor3a on 12/3/17.
 */

public class Score implements Serializable{
    private int score;
    private String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }
}
