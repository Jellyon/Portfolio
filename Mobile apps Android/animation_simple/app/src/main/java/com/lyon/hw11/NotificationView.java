package com.lyon.hw11;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        finish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
    }
}
