package com.example.josue.cardgame;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by josue on 12/4/2017.
 */

public class BGMService extends Service {

    public IBinder onBind(Intent intent){
        return null;
    }

    public void onCreate(){
        super.onCreate();


    }

}
