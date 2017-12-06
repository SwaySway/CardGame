package com.example.josue.cardgame;

import org.json.*;

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

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put("id", name);
        json.put("score", score);
        return json;
    }



}
