package com.example.josue.cardgame;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;

/**
 * Created by josue on 12/4/2017.
 */

public class
BGMService extends Service {

    private static final String TAG = null;
    MediaPlayer player;

    // This method binds a service with an activity, so returning null
    // tells us that we don't want the music bound to any one activity
    // (we'll be using the music toggle in the main menu)
    public IBinder onBind(Intent i){
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bgm);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        //player.start();

    }
    // This suppress comment is the only thing keeping the method from being uncompilable
    // Remove when we can figure out what to return, Android Studio has some suggestions
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        // This will start the player with the initialization of the game
        return 1;
    }

    // I think this is unnecessary since we aren't binding anything
    public IBinder onUnBind(Intent arg0) {
        return null;
    }

    @Override
    // Called when the music toggle is set to "off"
    public void onDestroy() {
        player.stop();
        // Release the resources the app is using for playing media
        player.release();
    }

    @Override
    // A consideration for when the RAM is too low to execute tasks efficiently,
    // will check later
    public void onLowMemory() {

    }
}