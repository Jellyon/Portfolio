package com.lyon.hw14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void toggle(View v){
        SimpleDrawingView sdv =  (SimpleDrawingView) findViewById(R.id.simpleDrawingView1);
        if(sdv.mode)
            sdv.mode = false;
        else
            sdv.mode = true;
        sdv.setupPaint();
    }
}