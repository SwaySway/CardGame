package com.example.josue.cardgame;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Highscore extends AppCompatActivity {

    private ArrayList<Score> scores = new ArrayList<Score>();
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        TextView highScores = (TextView) findViewById(R.id.highScores);
        Button back = (Button) findViewById(R.id.backButton);

        int score = getIntent().getIntExtra("SCORE", 0);
        String name = getIntent().getStringExtra("NAME");
//        loadScore();
        scores.add(new Score(score, name));
        highScores.setText(getHighscores());

    }

    public String getHighscores(){
        String highscores = "";
        int count = 5;

        ArrayList<Score> scores = getScores();

        for(int i = 0; i < count; i++){
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

    public void loadScore(){
        try {
            ois = new ObjectInputStream(new FileInputStream("assets/highscore.txt"));
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
}
