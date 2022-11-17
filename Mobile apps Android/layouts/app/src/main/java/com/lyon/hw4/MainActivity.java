package com.lyon.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup buttonLayout = (ViewGroup) findViewById(R.id.buttons);

        for(int i=0; i<buttonLayout.getChildCount(); i++) {
            View button = (View) buttonLayout.getChildAt(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.toString().contains("/lbtn")) return;
                    else{
                        if(view.toString().contains("/tbtn")){
                            //launch 2nd activity and pause
                            startActivity(new Intent("form.intent.SecondActivity"));
                            onPause();
                        }
                        else if(view.toString().contains("/rbtn")){
                            //launch 3rd activity and pause
                            startActivity(new Intent("form.intent.ThirdActivity"));
                            onPause();
                        }
                    }
                }
            });
        }
    }

}