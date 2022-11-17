package com.lyon.hw4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ViewGroup buttonRow = (ViewGroup) findViewById(R.id.buttons);
        for (int i = 0; i < buttonRow.getChildCount(); i++) {
            View button = (View) buttonRow.getChildAt(i);
            button.setOnClickListener(view -> {
                if (view.toString().contains("/tbtn")) return;
                else {
                    if (view.toString().contains("/lbtn")) {
                        //close back to main activity
                        finish();
                    } else if (view.toString().contains("/rbtn")) {
                        //launch 3rd activity
                        startActivity(new Intent("form.intent.ThirdActivity"));

                        finish();
                    }
                }
            });
        }
    }
}
