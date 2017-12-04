package com.example.josue.cardgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Highscore extends AppCompatActivity {

    private ArrayList<Score> scores = new ArrayList<Score>();

    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        TextView highscoreLabel = (TextView) findViewById(R.id.highscoreLabel);
        TextView highScores = (TextView) findViewById(R.id.highScores);

        int score = getIntent().getIntExtra("Score", 0);
        String name = getIntent().getStringExtra("NAME");
        loadScore();
        scores.add(new Score(score, name));
        updateScore();
        highScores.setText(getHighscores());
//        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
//        int highScore = settings.getInt("HIGH_SCORE", 0);

    }

    /**
     * Read in scores from the file and store them in the scores list
     */
    public void loadScore(){
        try {
            ois = new ObjectInputStream(new FileInputStream("highscore.txt"));
            scores = (ArrayList<Score>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try{
                if(oos != null){
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the score into the file
     */
    public void updateScore(){
        try {
            oos = new ObjectOutputStream(new FileOutputStream("highscore.txt"));
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(oos != null) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Returns a string that will be used to print the top 5 highscores
    public String getHighscores(){
        String highscores = "";
        int count = 5;

        ArrayList<Score> scores = getScores();
        int size = scores.size();

        for(int i = 0; i < size; i++){
            highscores += (i + 1) + ".\t" + scores.get(i).getName() + "---" + scores.get(i).getScore();
        }
        return highscores;
    }

    public ArrayList<Score> getScores(){
        loadScore();
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
        return scores;
    }
}
