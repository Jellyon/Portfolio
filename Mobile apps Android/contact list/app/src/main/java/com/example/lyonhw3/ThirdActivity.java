package com.example.lyonhw3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lyonhw3.databinding.ActivityThirdBinding;

public class ThirdActivity extends Activity {
    String results= new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityThirdBinding bind = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
        String name = this.getIntent().getStringExtra("Name");
        String number = this.getIntent().getStringExtra("Number");
        String email = this.getIntent().getStringExtra("Email");
        results="Name:\n"+name+"\n\nPhone Number:\n"+number+"\n\nEmail:\n"+email;
        LinearLayout ll = findViewById(R.id.lay);
        TextView result = new TextView(this);
        result.setText(results);
        result.setGravity(Gravity.CENTER);
        result.setTextSize(30);
        result.setBackgroundColor(0);
        result.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.addView(result);
    }
}
