package com.example.josue.cardgame;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameDifficultyActivity extends AppCompatActivity {

    private SeekBar seekbar;
    private TextView seekIndicator;
    private Button startGame;
    private int numofCards;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_difficulty_acitivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        seekbar = findViewById(R.id.seekgamedifficulty);
        startGame = findViewById(R.id.launchGame);
        startGame.setText("Start!");
        seekbar.setProgress(0);
        seekbar.incrementProgressBy(2);
        seekbar.setMax(16 );
        state = getIntent().getIntExtra("State", 0);
        seekIndicator = findViewById(R.id.seekText);
        seekIndicator.setText(String.valueOf(seekbar.getProgress()+4));
        numofCards = seekbar.getProgress()+4;
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i+4;
                i = i/2;
                i = i * 2;
                numofCards = i;
                seekIndicator.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void launchGame(View view){
        if(state == 0) {
            Intent newIntent = new Intent(GameDifficultyActivity.this, Game.class);
            newIntent.putExtra("numofElements", numofCards);
            startActivity(newIntent);
        }
        else{
            Intent newIntent = new Intent(GameDifficultyActivity.this, Highscore.class);
            newIntent.putExtra("filenum", numofCards);
            startActivity(newIntent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
