package com.lyon.hw10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.ProgressBar;

import java.util.Random;

public class LocalService extends Service {
    private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();

    public class LocalBinder extends Binder {
        LocalService getService(){
            return LocalService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }
    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }
}
