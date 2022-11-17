package com.example.lyonhw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import com.example.lyonhw3.databinding.ActivityMainBinding;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int request=1;
    DBAdapter db = new DBAdapter(this);
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        populate();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        populate();
    }
    public void DisplayName(Cursor cursor){
        ll = findViewById(R.id.lay);
        TextView name = new TextView(this);
        name.setText(cursor.getString(1));
        name.setGravity(Gravity.CENTER);
        name.setTextSize(30);
        name.setBackgroundColor(0);
        name.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        name.setOnClickListener(v -> {
            Intent third = new Intent("form.intent.ThirdActivity");
            third.putExtra("Name", cursor.getString(2));
            third.putExtra("Email", cursor.getString(3) );
            third.putExtra("Number", cursor.getString(4));
            startActivity(third);
            onPause();
        });
        ll.addView(name, 0);
    }
    public void populate(){
        //will repopulate a list of all names
        ll = findViewById(R.id.lay);
        int n = ll.getChildCount();
        for(int i=0; i<n; i++){
            View current = ll.getChildAt(i);
            if(current instanceof TextView){
                //clear all textviews so it doesn't just duplicate contacts on display
                ll.removeView(current);
            }
        }
        db.open();
        Cursor cursor = db.getAll();
        if(cursor.moveToFirst()){
            do{//goes through each contact to display the names
                DisplayName(cursor);
            }while(cursor.moveToNext());
        }
        db.close();
    }
    /*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Context con = getApplicationContext();
        if (requestCode == request) {
            if(resultCode == RESULT_OK) {
                // if data was added, display it here:
                LinearLayout ll = findViewById(R.id.lay);
                TextView result = new TextView(this);
                //result.setText(data.getCharSequenceExtra("Number"));
                result.setText(data.getData().toString());
                result.setGravity(Gravity.CENTER);
                result.setTextSize(30);
                result.setBackgroundColor(0);
                result.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                result.setOnClickListener(v -> {
                    Intent third = new Intent("form.intent.ThirdActivity");
                    third.putExtra("Name", data.getStringExtra("Name"));
                    third.putExtra("Number", data.getStringExtra("Number"));
                    third.putExtra("Email", data.getStringExtra("Email"));
                    startActivity(third);
                    onPause();
                });
                ll.addView(result, 0);

                Toast.makeText(con, data.getData().toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }
    */

    public void AddContact(View form){
        //startActivityForResult(new Intent("form.intent.SecondActivity"), request);
        startActivity(new Intent("form.intent.SecondActivity"));
        onPause();
    }

}