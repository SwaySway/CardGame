package com.example.josue.cardgame;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.media.MediaPlayer;

/**
 * Created by josue on 12/4/2017.
 */

public class BGMService extends Service {

    private static final String TAG = null;
    MediaPlayer player;

    public IBinder onBind(Intent i){
        return null;
    }

//public void onCreate(){
//super.onCreate();
//
//
//}
//
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.bgm);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }
    // This suppress comment is the only thing keeping the method from being uncompilable
    // Remove when we can figure out what to return, Android Studio has some suggestions
    @SuppressLint("WrongConstant")
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return 1;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {

    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
