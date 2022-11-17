package com.lyon.hw9;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText input;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBAdapter dba = new DBAdapter(this);
        dba.open();
        long id = dba.insertContact("Jeff Lyon", "jeff@jeff.com");
        id = dba.insertContact("Sam Lyon","Sam@jeff.com");
        //dba.close();
        Cursor cursor = dba.getAll();
        if(cursor.moveToFirst()){
            do{
                DisplayContact(cursor);
            }while(cursor.moveToNext());
        }/*
        cursor = dba.getContact(2);
        if(cursor.moveToFirst())
            DisplayContact(cursor);
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_SHORT).show();
        */dba.close();
        input = (EditText) findViewById(R.id.input);
    }

    public void DisplayContact(Cursor cursor){
        Toast.makeText(this, "id: "+ cursor.getString(0) + "\nName: " +
                cursor.getString(1) + "\nEmail: " + cursor.getString(2), Toast.LENGTH_SHORT).show();
    }
    public void onClickSave(View view){
        String inString = input.getText().toString();

        try{
            FileOutputStream fOut = openFileOutput("textfile.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            try{
                osw.write(inString);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            osw.flush();
            osw.close();
            Toast.makeText(getBaseContext(), "File Saved!", Toast.LENGTH_SHORT).show();
            input.setText("");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void onClickLoad(View view){
        try{
            FileInputStream fis = openFileInput("textfile.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String buffer ="";
            int index;
            while((index=isr.read(inputBuffer))>0){
                String read = String.copyValueOf(inputBuffer, 0, index);
                buffer += read;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            input.setText(buffer);
            input.findFocus();
            input.setSelection(buffer.length());
            Toast.makeText(getBaseContext(), "File Loaded!", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}