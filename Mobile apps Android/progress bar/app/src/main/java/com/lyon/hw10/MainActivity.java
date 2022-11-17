package com.lyon.hw10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LocalService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this, LocalService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop(){
        super.onStop();
        unbindService(connection);
        mBound = false;
    }
    public void onButtonClick(View view){
        if(mBound){
            ProgressBar pb = (ProgressBar) findViewById(R.id.pBar);
            pb.setProgress(0);
            Runnable weee = new Runnable() {
                @Override
                public void run() {
                    pb.incrementProgressBy(25);
                }
            };
            Handler h = new Handler();
            h.postDelayed(weee, 1500);

            Context context = this;
            Runnable result = new Runnable() {
                @Override
                public void run() {
                    h.postDelayed(weee, 100);
                    int num = mService.getRandomNumber();
                    h.postDelayed(weee, 200);
                    Toast.makeText(context, "number: " + num, Toast.LENGTH_SHORT).show();
                    h.postDelayed(weee, 300);
                }
            };
            h.postDelayed(result, 2000);
        }
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalService.LocalBinder binder = (LocalService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };


}