package com.lyon.hw4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

    }

    public void onClick(View view){
        if(view.toString().contains("/rbtn")) return;
        else{
            if(view.toString().contains("/lbtn"))
                finish();
            else if(view.toString().contains("/tbtn")){
                startActivity(new Intent("form.intent.SecondActivity"));
                finish();
            }
        }
    }
}
