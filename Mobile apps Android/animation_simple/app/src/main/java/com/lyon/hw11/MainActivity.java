package com.lyon.hw11;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    Button b1;
    String channel_name="testchannel";
    String channel_description="description here";
    String CHANNEL_ID = "jelyon";
    int notificationId = 123;
    boolean animating = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(view -> {
            addNotification();
            finish();
        });
        createNotificationChannel();

        ImageButton b2 = (ImageButton) findViewById(R.id.imageButton);
        b2.setOnClickListener(view -> {
            if(!animating) {
                ObjectAnimator spinout = ObjectAnimator.ofFloat(b2, "rotation", 365);
                spinout.setDuration(700);
                spinout.setInterpolator(new LinearInterpolator());
                spinout.setRepeatCount(ValueAnimator.INFINITE);
                spinout.setRepeatMode(ValueAnimator.RESTART);
                spinout.start();
                animating=true;
            }
        });


    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notManager = getSystemService(NotificationManager.class);
            notManager.createNotificationChannel(channel);

        }
    }
    void addNotification(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Jeff's app:")
                .setContentText("Jeff is cool!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, builder.build());
    }
}