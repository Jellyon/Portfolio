package com.example.lyonhw5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Don't click on my wrench!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        CreateMenu(menu);
        return true;
    }
    private void CreateMenu(Menu menu){
        MenuItem item1 = menu.add(0,0,0, "Item 1");
        item1.setIcon(R.mipmap.ic_launcher);
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item2 = menu.add(0,1,1, "Item 2");
        item2.setIcon(R.mipmap.ic_launcher);
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item3 = menu.add(0,2,2, "Item 3");
        item3.setIcon(R.mipmap.ic_launcher);
        item3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item4 = menu.add(0,3,3, "Item 4");
        item4.setIcon(R.mipmap.ic_launcher);
        item4.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item5 = menu.add(0,4,4, "Clear Screen");
        item4.setIcon(R.mipmap.ic_launcher);
        item4.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    }
    private boolean MenuChoice(MenuItem item){
        if (item.getItemId()>=0 && item.getItemId() < 5){
            Toast.makeText(this, "Item " + (item.getItemId()+1), Toast.LENGTH_SHORT).show();
            //return true;
        }
        //todo Homework 5 code here:
        TextView txt1 = (TextView) findViewById(R.id.txt1);
        TextView txt2 = (TextView) findViewById(R.id.txt2);
        TextView txt3 = (TextView) findViewById(R.id.txt3);
        TextView txt4 = (TextView) findViewById(R.id.txt4);

        switch (item.getItemId()){

            case 0:
                txt1.setVisibility(TextView.VISIBLE);
                return true;
            case 1:
                txt2.setVisibility(TextView.VISIBLE);
                return true;
            case 2:
                txt3.setVisibility(TextView.VISIBLE);
                return true;
            case 3:
                txt4.setVisibility(TextView.VISIBLE);
                return true;
            case 4:
                txt1.setVisibility(TextView.INVISIBLE);
                txt2.setVisibility(TextView.INVISIBLE);
                txt3.setVisibility(TextView.INVISIBLE);
                txt4.setVisibility(TextView.INVISIBLE);
                return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return MenuChoice(item);
    }
}